package com.romanticpipe.reviewcanvas.common.security;

import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Component
public final class AccessPath {

	@Getter
	private final MultiValueMap<String, HttpMethod> shopAdminAllowedPath;
	@Getter
	private final MultiValueMap<String, HttpMethod> superAdminAllowedPath;

	private AccessPath() {
		shopAdminAllowedPath = initShopAdminAllowedPath();
		superAdminAllowedPath = initSuperAdminAllowedPath();
	}

	private MultiValueMap<String, HttpMethod> initShopAdminAllowedPath() {
		MultiValueMap<String, HttpMethod> shopAdminAllowedPath = new LinkedMultiValueMap<>();
		// review
		shopAdminAllowedPath.put("/api/v1/users/{userId}/reviews", List.of(HttpMethod.GET));
		// shop-admin
		shopAdminAllowedPath.put("/api/v1/shop-admin", List.of(HttpMethod.GET, HttpMethod.PATCH));
		// auth
		shopAdminAllowedPath.put("/api/v1/logout", List.of(HttpMethod.POST));
		shopAdminAllowedPath.put("/api/v1/auth/check", List.of(HttpMethod.GET));
		// review-property
		shopAdminAllowedPath.put("/api/v1/shop-admin/review-properties", List.of(HttpMethod.GET));
		// review-title
		shopAdminAllowedPath.put("/api/v1/shop-admin/review-title", List.of(HttpMethod.GET, HttpMethod.PATCH));
		shopAdminAllowedPath.put("/api/v1/shop-admin/review-title/reset", List.of(HttpMethod.PATCH));
		// review-column
		shopAdminAllowedPath.put("/api/v1/shop-admin/review-column", List.of(HttpMethod.GET, HttpMethod.PATCH));
		shopAdminAllowedPath.put("/api/v1/shop-admin/review-column/reset", List.of(HttpMethod.PATCH));
		// review-layout
		shopAdminAllowedPath.put("/api/v1/shop-admin/review-layout", List.of(HttpMethod.PATCH, HttpMethod.GET));
		shopAdminAllowedPath.put("/api/v1/shop-admin/review-layout/initialize", List.of(HttpMethod.PATCH));
		// review-container
		shopAdminAllowedPath.put("/api/v1/shop-admin/review-container", List.of(HttpMethod.PATCH, HttpMethod.GET));
		shopAdminAllowedPath.put("/api/v1/shop-admin/review-container/reset", List.of(HttpMethod.PATCH));
		// review-design
		shopAdminAllowedPath.put("/api/v1/shop-admin/review-design-view", List.of(HttpMethod.PATCH, HttpMethod.GET));
		shopAdminAllowedPath.put("/api/v1/shop-admin/review-design-view/reset", List.of(HttpMethod.PATCH));
		shopAdminAllowedPath.put("/api/v1/shop-admin/review-design-write", List.of(HttpMethod.PATCH, HttpMethod.GET));
		shopAdminAllowedPath.put("/api/v1/shop-admin/review-design-write/reset", List.of(HttpMethod.PATCH));
		return shopAdminAllowedPath;
	}

	private MultiValueMap<String, HttpMethod> initSuperAdminAllowedPath() {
		LinkedMultiValueMap<String, HttpMethod> superAdminAllowedPath = new LinkedMultiValueMap<>();
		// review
		superAdminAllowedPath.put("/api/v1/users/{userId}/reviews", List.of(HttpMethod.GET));
		// shop-admin
		// auth
		superAdminAllowedPath.put("/api/v1/logout", List.of(HttpMethod.POST));
		superAdminAllowedPath.put("/api/v1/auth/check", List.of(HttpMethod.GET));
		return superAdminAllowedPath;
	}
}
