package com.romanticpipe.reviewcanvas.common.security;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.romanticpipe.reviewcanvas.domain.AdminInterface;
import com.romanticpipe.reviewcanvas.domain.Role;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.TokenInfoResponse;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.service.ShopAdminValidator;
import com.romanticpipe.reviewcanvas.service.SuperAdminValidator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenProvider implements InitializingBean {

	private static final String AUTHORITIES_KEY = "auth";
	private static final String USER_INFO = "shopAdminId";

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

	public TokenInfoResponse createToken(String email, ShopAdmin shopAdmin) {
		// 스프링 시큐리티 처리
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(String.valueOf(Role.USER)));

		OAuth2User userDetails = createOAuth2UserByJson(authorities, email);
		OAuth2AuthenticationToken auth = configureAuthentication(userDetails, authorities);
		// JWT 토큰 생성
		String auths = auth.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.joining(","));

		long now = (new Date()).getTime();
		Date accessTokenValidity = new Date(now + 1000 * this.accessTokenValidityTime);
		Date refreshTokenValidity = new Date(now + 1000 * this.refreshTokenValidityTime);
		String accessToken = Jwts.builder()
			.setSubject(auth.getName())
			.claim(AUTHORITIES_KEY, auths)
			.signWith(key, SignatureAlgorithm.HS512)
			.setExpiration(accessTokenValidity)
			.compact();

		String refreshToken = Jwts.builder()
			.setExpiration(refreshTokenValidity)
			.claim(USER_INFO, shopAdmin.getId())
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
		return TokenInfoResponse.from("Bearer", accessToken, refreshToken, refreshTokenValidityTime);

	}

	public Authentication getAuthentication(String token) {
		Claims claims = parseClaims(token);
		Collection<? extends GrantedAuthority> authorities =
			Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		System.out.println(authorities.stream()
			.anyMatch(authority -> authority.getAuthority().equals(Role.USER.toString())));
		AdminInterface admin;
		if (authorities.stream()
			.anyMatch(authority -> authority.getAuthority().equals(Role.USER.toString()))) {
			admin = this.shopAdminValidator.findByEmail(claims.getSubject());
		} else {
			admin = this.superAdminValidator.findByEmail(claims.getSubject());
		}

		return new UsernamePasswordAuthenticationToken(new CustomUserDetails(admin), token, authorities);
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (SecurityException | MalformedJwtException e) {
			throw new BusinessException(JwtErrorCode.MAL_FORMED_TOKEN);
		} catch (ExpiredJwtException e) {
			throw new BusinessException(JwtErrorCode.EXPIRED_TOKEN);
		} catch (UnsupportedJwtException e) {
			throw new BusinessException(JwtErrorCode.UNSUPPORTED_TOKEN);
		} catch (IllegalArgumentException e) {
			throw new BusinessException(JwtErrorCode.ILLEGAL_TOKEN);
		} catch (Exception e) {
			throw e;
		}
	}

	private Claims parseClaims(String accessToken) {
		try {
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}

	private OAuth2User createOAuth2UserByJson(List<GrantedAuthority> authorities, String email) {
		Map<String, Object> memberMap = new HashMap<>();
		memberMap.put("email", email);
		authorities.add(new SimpleGrantedAuthority(String.valueOf(Role.USER)));
		return new DefaultOAuth2User(authorities, memberMap, "email");
	}

	public OAuth2AuthenticationToken configureAuthentication(OAuth2User userDetails,
		List<GrantedAuthority> authorities) {
		OAuth2AuthenticationToken auth = new OAuth2AuthenticationToken(userDetails, authorities, "email");
		auth.setDetails(userDetails);
		SecurityContextHolder.getContext().setAuthentication(auth);
		return auth;
	}
}
