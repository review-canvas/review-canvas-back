package com.romanticpipe.reviewcanvas.common.security;

import com.romanticpipe.reviewcanvas.common.security.exception.SecurityErrorCode;
import com.romanticpipe.reviewcanvas.common.security.exception.TokenException;
import com.romanticpipe.reviewcanvas.common.security.exception.TokenExpiredException;
import com.romanticpipe.reviewcanvas.domain.AdminRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class TokenProvider {

	private final Duration accessTokenValidityTime;

	private final Duration refreshTokenValidityTime;

	private final Key secretKey;

	public TokenProvider(@Value("${spring.jwt.secret}") String secret,
						 @Value("${spring.jwt.access-token-validity-time}") Duration accessTokenValidityTime,
						 @Value("${spring.jwt.refresh-token-validity-time}") Duration refreshTokenValidityTime) {
		this.accessTokenValidityTime = accessTokenValidityTime;
		this.refreshTokenValidityTime = refreshTokenValidityTime;
		this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	}

	public String createAccessToken(Integer adminId, AdminRole adminRole) {
		return createToken(adminId, adminRole, "ACCESS", accessTokenValidityTime);
	}

	public String createRefreshToken(Integer adminId, AdminRole adminRole) {
		return createToken(adminId, adminRole, "REFRESH", refreshTokenValidityTime);
	}

	public String createNewAccessTokenFromRefreshToken(String refreshToken) {
		Claims claims = validateToken(JwtType.REFRESH, refreshToken).getBody();

		Integer adminId = Integer.parseInt((String) claims.get(Claims.SUBJECT));
		AdminRole adminRole = AdminRole.valueOf((String) claims.get(CustomClaims.ROLE));
		return createAccessToken(adminId, adminRole);
	}

	private String createToken(Integer adminId, AdminRole adminRole, String tokenType, Duration tokenValidityTime) {
		Instant now = Instant.now();
		Date currentDate = Date.from(now);
		Date expiredDate = Date.from(now.plus(tokenValidityTime));

		return Jwts.builder()
			.setHeader(Map.of("typ", "JWT"))
			.setSubject(String.valueOf(adminId))
			.setIssuedAt(currentDate)
			.setExpiration(expiredDate)
			.claim(CustomClaims.ROLE, adminRole)
			.claim(CustomClaims.TOKEN_TYPE, tokenType)
			.signWith(secretKey, SignatureAlgorithm.HS512)
			.compact();
	}

	public Authentication getAuthentication(String token) {
		Claims claims = parseClaims(token).getBody();
		Integer adminId = Integer.parseInt(claims.get(Claims.SUBJECT).toString());
		Collection<? extends GrantedAuthority> authorities = getAuthorities(claims);
		return new JwtAuthenticationToken(adminId, authorities);
	}

	public Jws<Claims> validateToken(JwtType jwtType, String token) {
		try {
			Jws<Claims> claimsJws = parseClaims(token);
			validateTokenType(claimsJws, jwtType);
			return claimsJws;
		} catch (ExpiredJwtException e) {
			throw new TokenExpiredException();
		} catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
			throw new TokenException(SecurityErrorCode.INVALID_TOKEN);
		}
	}

	private Jws<Claims> parseClaims(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(secretKey)
			.build()
			.parseClaimsJws(token);
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Claims claims) {
		String adminRole = claims.get(CustomClaims.ROLE).toString();
		return List.of(new SimpleGrantedAuthority(adminRole));
	}

	private void validateTokenType(Jws<Claims> claimsJws, JwtType jwtType) {
		String tokenType = String.valueOf(claimsJws.getBody().get(CustomClaims.TOKEN_TYPE));
		if (!jwtType.name().equals(tokenType)) {
			throw new TokenException(SecurityErrorCode.DISALLOWED_TOKEN_TYPE);
		}
	}
}
