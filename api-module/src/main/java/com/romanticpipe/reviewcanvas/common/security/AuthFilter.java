package com.romanticpipe.reviewcanvas.common.security;

import com.romanticpipe.reviewcanvas.common.security.exception.SecurityErrorCode;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
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
	private static final String BEARER_PREFIX = "Bearer ";

	private final TokenProvider tokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
									FilterChain filterChain) throws BusinessException, ServletException, IOException {
		authentication(request);
		filterChain.doFilter(request, response);
	}

	private void authentication(HttpServletRequest request) {
		String accessToken = validateJwt(request);

		tokenProvider.validateToken(accessToken);

		Authentication authentication = tokenProvider.getAuthentication(accessToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String validateJwt(HttpServletRequest request) {
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(BEARER_PREFIX)) {
			return authorizationHeader.substring(BEARER_PREFIX.length());
		}
		throw new BusinessException(SecurityErrorCode.NON_BEARER);
	}
}
