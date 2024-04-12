package com.romanticpipe.reviewcanvas.common.security;

import static org.springframework.http.HttpHeaders.*;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.romanticpipe.reviewcanvas.domain.AdminInterface;
import com.romanticpipe.reviewcanvas.exception.BusinessException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
	private final String BEARER_PREFIX = "Bearer ";
	private final String ATTRIBUTE_NAME = "exception";
	private final String HEADER_NAME = "Authorization";

	private final TokenProvider tokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws BusinessException, ServletException, IOException {
		String jwt;
		try {
			jwt = resolveToken(request);
			if (StringUtils.hasText(jwt)) {
				Claims claims = tokenProvider.validateToken(jwt).getBody();
				Authentication authentication = tokenProvider.getAuthentication(claims, jwt);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			request.setAttribute(ATTRIBUTE_NAME, SecurtyErrorCode.MAL_FORMED_TOKEN);
		} catch (IllegalArgumentException e) {
			request.setAttribute(ATTRIBUTE_NAME, SecurtyErrorCode.ILLEGAL_TOKEN);
		} catch (ExpiredJwtException e) {
			AdminInterface admin = tokenProvider.getAdmin(e.getClaims());
			if (tokenProvider.isExpiredById(admin.getAdminAuthId())) {
				request.setAttribute(ATTRIBUTE_NAME, SecurtyErrorCode.EXPIRED_TOKEN);
			} else {
				jwt = tokenProvider.createToken(admin);
				Claims claims = tokenProvider.validateToken(jwt).getBody();
				Authentication authentication = tokenProvider.getAuthentication(claims, jwt);
				SecurityContextHolder.getContext().setAuthentication(authentication);

				response.setHeader(HEADER_NAME, jwt);
			}
		} catch (UnsupportedJwtException e) {
			request.setAttribute(ATTRIBUTE_NAME, SecurtyErrorCode.UNSUPPORTED_TOKEN);
		} catch (Exception e) {
			request.setAttribute(ATTRIBUTE_NAME, SecurtyErrorCode.UNKNOWN_ERROR);
		}
		filterChain.doFilter(request, response);
	}

	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
			return bearerToken.substring(BEARER_PREFIX.length());
		}
		throw new BusinessException(SecurtyErrorCode.NON_BEARER);
	}
}
