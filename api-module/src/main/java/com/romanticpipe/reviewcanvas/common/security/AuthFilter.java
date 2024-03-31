package com.romanticpipe.reviewcanvas.common.security;

import static com.romanticpipe.reviewcanvas.common.security.JwtException.*;
import static org.springframework.http.HttpHeaders.*;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.romanticpipe.reviewcanvas.exception.BusinessException;

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
	private final TokenProvider tokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws BusinessException, ServletException, IOException {
		String jwt = resolveToken(request);
		String requestURI = request.getRequestURI();
		try {
			if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				boolean isAdditionalInfoProvided = tokenProvider.getAdditionalInfoProvided(jwt);
				if (isAdditionalInfoProvided) {
					Authentication authentication = tokenProvider.getAuthentication(jwt);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				} else {
					throw new BusinessException(ADDITIONAL_REQUIRED_TOKEN);
				}
			}

		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			request.setAttribute("exception", MAL_FORMED_TOKEN.getCode());
		} catch (BusinessException e) {
			if (e.getErrorCode() == ILLEGAL_TOKEN) {
				request.setAttribute("exception", ILLEGAL_TOKEN.getCode());
			}
			request.setAttribute("exception", ADDITIONAL_REQUIRED_TOKEN.getCode());
		} catch (ExpiredJwtException e) {
			request.setAttribute("exception", EXPIRED_TOKEN.getCode());
		} catch (UnsupportedJwtException e) {
			request.setAttribute("exception", UNSUPPORTED_TOKEN.getCode());
		} catch (Exception e) {
			request.setAttribute("exception", UNKNOWN_ERROR.getCode());
		}
		filterChain.doFilter(request, response);
	}

	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
			return bearerToken.substring(BEARER_PREFIX.length());
		}
		return null;
	}
}
