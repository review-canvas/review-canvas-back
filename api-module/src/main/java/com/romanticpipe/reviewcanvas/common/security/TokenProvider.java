package com.romanticpipe.reviewcanvas.common.security;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.romanticpipe.reviewcanvas.domain.AdminAuth;
import com.romanticpipe.reviewcanvas.domain.AdminInterface;
import com.romanticpipe.reviewcanvas.domain.Role;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.service.ShopAdminValidator;
import com.romanticpipe.reviewcanvas.service.SuperAdminValidator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenProvider implements InitializingBean {

	private static final String AUTHORITIES_KEY = "auth";
	private static final String USER_INFO = "AdminEmail";

	private final ShopAdminValidator shopAdminValidator;
	private final SuperAdminValidator superAdminValidator;

	@Value("${spring.jwt.secret}")
	private String secret;

	@Value("${spring.jwt.access-token-validity-in-seconds}")
	private long accessTokenValidityTime;

	@Value("${spring.jwt.refresh-token-validity-in-seconds}")
	private long refreshTokenValidityTime;

	private Key key;

	@Override
	public void afterPropertiesSet() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	public String createToken(ShopAdmin shopAdmin, Role role, AdminAuth adminAuth) {
		// 스프링 시큐리티 처리
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(String.valueOf(role)));
		// 사용자 인증 정보 생성
		UsernamePasswordAuthenticationToken auth = configureAuthentication(shopAdmin, authorities);

		// JWT 토큰 생성
		String auths = auth.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.joining(","));

		long now = (new Date()).getTime();
		Date accessTokenValidity = new Date(now + 1000 * this.accessTokenValidityTime);
		Date refreshTokenValidity = new Date(now + 1000 * this.refreshTokenValidityTime);

		String accessToken = Jwts.builder()
			.setExpiration(accessTokenValidity)
			.setSubject(auth.getName())
			.claim(AUTHORITIES_KEY, auths)
			.claim(USER_INFO, shopAdmin.getEmail())
			.signWith(key, SignatureAlgorithm.HS512)
			.compact();

		String refreshToken = adminAuth.getRefreshToken();

		if (refreshToken.isEmpty() || checkTokenExpired(refreshToken)) {
			refreshToken = Jwts.builder()
				.setExpiration(refreshTokenValidity)
				.claim(USER_INFO, shopAdmin.getId())
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();

			adminAuth.setRefreshToken(refreshToken);
		}
		return accessToken;
	}

	private boolean checkTokenExpired(String refreshToken) {
		try {
			validateToken(refreshToken);
			return false;
		} catch (ExpiredJwtException e) {
			return true;
		}
	}

	public Authentication getAuthentication(String token) {
		Claims claims = parseClaims(token);
		Collection<? extends GrantedAuthority> authorities =
			Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		AdminInterface admin;
		if (authorities.stream()
			.anyMatch(authority -> authority.getAuthority().equals(Role.USER.toString()))) {
			admin = this.shopAdminValidator.isExsitUser(claims.get(USER_INFO).toString());
		} else {
			admin = this.superAdminValidator.isExsitUser(claims.get(USER_INFO).toString());
		}
		return new UsernamePasswordAuthenticationToken(admin, token, authorities);
	}

	public boolean validateToken(String token) throws BusinessException {
		Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
		return true;
	}

	private Claims parseClaims(String accessToken) {
		try {
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}

	public UsernamePasswordAuthenticationToken configureAuthentication(ShopAdmin shopAdmin,
		List<GrantedAuthority> authorities) {
		UsernamePasswordAuthenticationToken auth =
			new UsernamePasswordAuthenticationToken(shopAdmin, key, authorities);
		SecurityContextHolder.getContext().setAuthentication(auth);
		return auth;
	}
}
