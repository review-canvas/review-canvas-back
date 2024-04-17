package com.romanticpipe.reviewcanvas.common.security;

import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

@Component
public final class AccessPath {

	@Getter
	private final MultiValueMap<String, HttpMethod> allAllowedPath;
	@Getter
	private final MultiValueMap<String, HttpMethod> shopAdminDeniedPath;

	private final List<HttpMethod> allMethod =
		Arrays.asList(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE);

	private AccessPath() {
		allAllowedPath = initAllAllowedPath();
		shopAdminDeniedPath = initShopAdminDeniedPath();
	}

	private MultiValueMap<String, HttpMethod> initAllAllowedPath() {
		MultiValueMap<String, HttpMethod> allAllowedPath = new LinkedMultiValueMap<>();
		// swagger
		allAllowedPath.put("/swagger-ui/**", allMethod);
		allAllowedPath.put("/v3/api-docs/**", allMethod);

		// health check
		allAllowedPath.put("/api/v1/health", allMethod);

		// auth
		allAllowedPath.put("/api/v1/shop-admin/login", List.of(HttpMethod.POST));
		allAllowedPath.put("/api/v1/super-admin/login", List.of(HttpMethod.POST));
		allAllowedPath.put("/api/v1/reissue-access-token", List.of(HttpMethod.POST));

		// shop admin
		allAllowedPath.put("/api/v1/shop-admin/sign-up", List.of(HttpMethod.POST));
		allAllowedPath.put("/api/v1/shop-admin/email-check", List.of(HttpMethod.GET));

		// review
		allAllowedPath.put("/api/v1/products/{productId}/reviews", List.of(HttpMethod.GET, HttpMethod.POST));
		allAllowedPath.put("/api/v1/reviews/{reviewId}", List.of(HttpMethod.PATCH));
		allAllowedPath.put("/api/v1/users/{userId}/reviews", List.of(HttpMethod.GET));
		return allAllowedPath;
	}

	private MultiValueMap<String, HttpMethod> initShopAdminDeniedPath() {
		LinkedMultiValueMap<String, HttpMethod> shopAdminDeniedPath = new LinkedMultiValueMap<>();
		return shopAdminDeniedPath;
	}
}
