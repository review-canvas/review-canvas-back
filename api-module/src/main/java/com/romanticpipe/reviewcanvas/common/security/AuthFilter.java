package com.romanticpipe.reviewcanvas.common.security;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
	private final String BEARER_PREFIX = "Bearer ";
	private final String ATTRIBUTE_NAME = "exception";

	private final TokenProvider tokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
									FilterChain filterChain) throws BusinessException, ServletException, IOException {
		try {
			String jwt = findToken(request);
			Claims claims = tokenProvider.validateToken(jwt).getBody();
			Authentication authentication = tokenProvider.getAuthentication(claims, jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			request.setAttribute(ATTRIBUTE_NAME, SecurityErrorCode.MAL_FORMED_TOKEN);
		} catch (IllegalArgumentException e) {
			request.setAttribute(ATTRIBUTE_NAME, SecurityErrorCode.ILLEGAL_TOKEN);
		} catch (ExpiredJwtException e) {
			request.setAttribute(ATTRIBUTE_NAME, SecurityErrorCode.EXPIRED_TOKEN);
		} catch (UnsupportedJwtException e) {
			request.setAttribute(ATTRIBUTE_NAME, SecurityErrorCode.UNSUPPORTED_TOKEN);
		} catch (BusinessException e) {
			request.setAttribute(ATTRIBUTE_NAME, e.getErrorCode());
		} catch (Exception e) {
			request.setAttribute(ATTRIBUTE_NAME, SecurityErrorCode.UNKNOWN_ERROR);
		}
		filterChain.doFilter(request, response);
	}

	private String findToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
			return bearerToken.substring(BEARER_PREFIX.length());
		}
		throw new BusinessException(SecurityErrorCode.NON_BEARER);
	}
}
