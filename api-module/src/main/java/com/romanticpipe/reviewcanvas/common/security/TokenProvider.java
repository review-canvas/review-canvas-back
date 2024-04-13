package com.romanticpipe.reviewcanvas.common.security;

import java.security.Key;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.service.AdminAuthValidator;
import com.romanticpipe.reviewcanvas.service.ShopAdminValidator;
import com.romanticpipe.reviewcanvas.service.SuperAdminValidator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenProvider implements InitializingBean {

	private static final String AUTHORITIES_KEY = "auth";
	private static final String AUTH_ID = "authId";
	private static final String ADMIN_ID = "adminId";
	private static final String DELETED_TOKEN = "DELETED_TOKEN";

	private final AdminAuthValidator adminAuthValidator;
	private final ShopAdminValidator shopAdminValidator;
	private final SuperAdminValidator superAdminValidator;

	@Value("${spring.jwt.secret}")
	private String secret;

	@Value("${spring.jwt.access-token-validity}")
	private Duration accessTokenValidityTime;

	@Value("${spring.jwt.refresh-token-validity}")
	private Duration refreshTokenValidityTime;

	private Key secretKey;

	@Override
	public void afterPropertiesSet() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		this.secretKey = Keys.hmacShaKeyFor(keyBytes);
	}

	public String createToken(AdminInterface admin) {
		// 스프링 시큐리티 처리
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(String.valueOf(admin.getRole())));
		// 사용자 인증 정보 생성
		UsernamePasswordAuthenticationToken auth = configureAuthentication(admin, authorities);

		// JWT 토큰 생성
		String auths = auth.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.joining(","));

		String accessToken = Jwts.builder()
			.setExpiration(getTokenValidityDate(this.accessTokenValidityTime))
			.setSubject(auth.getName())
			.claim(AUTHORITIES_KEY, auths)
			.claim(AUTH_ID, admin.getAdminAuthId())
			.claim(ADMIN_ID, admin.getId())
			.signWith(secretKey, SignatureAlgorithm.HS512)
			.compact();

		return accessToken;
	}

	public void createRefreshToken(AdminAuth adminAuth, Long adminId) {
		adminAuth.setRefreshToken(Jwts.builder()
			.setExpiration(getTokenValidityDate(this.refreshTokenValidityTime))
			.claim(AUTH_ID, adminAuth.getId())
			.claim(ADMIN_ID, adminId)
			.signWith(secretKey, SignatureAlgorithm.HS256)
			.compact());
	}

	private Date getTokenValidityDate(Duration duration) {
		long now = (new Date()).getTime();
		return new Date(now + duration.toMillis());
	}

	public Authentication getAuthentication(Claims claims, String token) {
		Collection<? extends GrantedAuthority> authorities = getAuthorities(claims);
		AdminInterface admin = getAdminByClaims(claims);
		return new UsernamePasswordAuthenticationToken(admin, token, authorities);
	}

	public AdminInterface getAdminByClaims(Claims claims) {
		Collection<? extends GrantedAuthority> authorities = getAuthorities(claims);
		long authId = Long.parseLong(claims.get(AUTH_ID).toString());

		return isSuperAdmin(authorities) ? this.superAdminValidator.validByAuthId(authId) :
			this.shopAdminValidator.validByAuthId(authId);
	}

	private List<SimpleGrantedAuthority> getAuthorities(Claims claims) {
		return Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toList());
	}

	private boolean isSuperAdmin(Collection<? extends GrantedAuthority> authorities) {
		return authorities.stream()
			.anyMatch(authority -> authority.getAuthority().equals(Role.SUPER_ADMIN_ROLE.toString()));
	}

	public void checkTokenExpired(long authId) {
		String refreshToken = Optional.ofNullable(this.adminAuthValidator.findById(authId))
			.map(AdminAuth::getRefreshToken)
			.orElseThrow(() -> new BusinessException(SecurityErrorCode.EXPIRED_TOKEN));
		try {
			validateToken(refreshToken);
		} catch (ExpiredJwtException | MalformedJwtException e) {
			throw new BusinessException(SecurityErrorCode.EXPIRED_TOKEN);
		}
	}

	public UsernamePasswordAuthenticationToken configureAuthentication(AdminInterface admin,
		List<GrantedAuthority> authorities) {
		UsernamePasswordAuthenticationToken auth =
			new UsernamePasswordAuthenticationToken(admin, secretKey, authorities);
		SecurityContextHolder.getContext().setAuthentication(auth);
		return auth;
	}

	public Jws<Claims> validateToken(String token) throws BusinessException, MalformedJwtException {
		return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
	}

	public Claims getByClaimsExpiredToken(String token) {
		try {
			return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
}
