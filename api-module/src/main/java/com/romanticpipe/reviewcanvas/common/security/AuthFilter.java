package com.romanticpipe.reviewcanvas.common.security;

import com.romanticpipe.reviewcanvas.admin.domain.AdminRole;
import com.romanticpipe.reviewcanvas.common.security.exception.SecurityErrorCode;
import com.romanticpipe.reviewcanvas.common.security.exception.TokenException;
import com.romanticpipe.reviewcanvas.common.security.exception.TokenExpiredException;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
		authentication(request, response);
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return !isRequestMatch(accessPath.getShopAdminAllowedPath(), request)
			&& !isRequestMatch(accessPath.getSuperAdminAllowedPath(), request);
	}

	private void authentication(HttpServletRequest request, HttpServletResponse response) {
		String accessToken = findAccessToken(request);

		try {
			tokenProvider.validateToken(JwtType.ACCESS, accessToken);
		} catch (TokenExpiredException e) {
			Cookie[] cookies = Optional.ofNullable(request.getCookies()).orElseThrow(() -> e);
			String refreshToken = Arrays.stream(cookies)
				.filter(cookie -> CustomCookieName.REFRESH_TOKEN.equals(cookie.getName()))
				.findAny()
				.map(Cookie::getValue)
				.orElseThrow(() -> e);

			accessToken = tokenProvider.createNewAccessTokenFromRefreshToken(refreshToken);
			response.setHeader("X-New-AccessToken", accessToken);
		}

		Authentication authentication = tokenProvider.getAuthentication(accessToken);
		validateAdminHasAccessPermission(request, authentication);

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String findAccessToken(HttpServletRequest request) {
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
		boolean hasAccessPermissionAllowed = authentication.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.map(AdminRole::valueOf)
			.anyMatch(authority -> {
				if (authority.equals(AdminRole.ROLE_SHOP_ADMIN)) {
					return isRequestMatch(accessPath.getShopAdminAllowedPath(), request);
				}
				if (authority.equals(AdminRole.ROLE_SUPER_ADMIN)) {
					return isRequestMatch(accessPath.getSuperAdminAllowedPath(), request);
				}
				return false;
			});

		if (!hasAccessPermissionAllowed) {
			throw new TokenException(SecurityErrorCode.AUTHORITY_NOT_FOUND);
		}
	}

}
