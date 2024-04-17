package com.romanticpipe.reviewcanvas.common.security;

import com.romanticpipe.reviewcanvas.common.security.exception.SecurityErrorCode;
import com.romanticpipe.reviewcanvas.common.security.exception.TokenException;
import com.romanticpipe.reviewcanvas.domain.AdminRole;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
	private static final String BEARER_PREFIX = "Bearer ";
	private final AntPathMatcher antPathMatcher = new AntPathMatcher();
	private final TokenProvider tokenProvider;
	private final AccessPath accessPath;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
									FilterChain filterChain) throws BusinessException, ServletException, IOException {
		authentication(request);
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return isRequestMatch(accessPath.getAllAllowedPath(), request);
	}

	private void authentication(HttpServletRequest request) {
		String accessToken = validateJwt(request);

		tokenProvider.validateToken(JwtType.ACCESS, accessToken);

		Authentication authentication = tokenProvider.getAuthentication(accessToken);
		validateAdminHasAccessPermission(request, authentication);

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String validateJwt(HttpServletRequest request) {
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(BEARER_PREFIX)) {
			return authorizationHeader.substring(BEARER_PREFIX.length());
		}
		throw new TokenException(SecurityErrorCode.NON_BEARER);
	}

	private boolean isRequestMatch(MultiValueMap<String, HttpMethod> ignoredPaths, HttpServletRequest request) {
		return ignoredPaths.keySet().stream()
			.anyMatch(ignoredUri ->
				isMatchUri(ignoredUri, request) && isMatchMethod(ignoredPaths.get(ignoredUri), request));
	}

	private boolean isMatchUri(String ignoredUri, HttpServletRequest requestUri) {
		return antPathMatcher.match(ignoredUri, requestUri.getRequestURI());
	}

	private boolean isMatchMethod(List<HttpMethod> ignoredPathList, HttpServletRequest request) {
		return ignoredPathList.stream().anyMatch(httpMethod -> httpMethod.matches(request.getMethod()));
	}

	private void validateAdminHasAccessPermission(HttpServletRequest request, Authentication authentication) {
		boolean hasAccessPermissionDenied = authentication.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.map(AdminRole::valueOf)
			.anyMatch(authority -> authority == AdminRole.ROLE_SHOP_ADMIN
				&& isRequestMatch(accessPath.getShopAdminDeniedPath(), request));

		if (hasAccessPermissionDenied) {
			throw new TokenException(SecurityErrorCode.AUTHORITY_NOT_FOUND);
		}
	}

}
