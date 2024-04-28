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
		superAdminAllowedPath = initShopAdminDeniedPath();
	}

	private MultiValueMap<String, HttpMethod> initShopAdminAllowedPath() {
		MultiValueMap<String, HttpMethod> shopAdminAllowedPath = new LinkedMultiValueMap<>();
		shopAdminAllowedPath.put("/api/v1/users/{userId}/reviews", List.of(HttpMethod.GET));
		shopAdminAllowedPath.put("/api/v1/shop-admin/review-design/{reviewDesignId}", List.of(HttpMethod.PATCH));
		shopAdminAllowedPath.put("/api/v1/shop-admin/review-visibility/title", List.of(HttpMethod.GET));
		shopAdminAllowedPath.put("/api/v1/shop-admin/review-design/theme-list", List.of(HttpMethod.GET));
		shopAdminAllowedPath.put("/api/v1/logout", List.of(HttpMethod.POST));
		return shopAdminAllowedPath;
	}

	private MultiValueMap<String, HttpMethod> initShopAdminDeniedPath() {
		LinkedMultiValueMap<String, HttpMethod> superAdminAllowedPath = new LinkedMultiValueMap<>();
		superAdminAllowedPath.put("/api/v1/users/{userId}/reviews", List.of(HttpMethod.GET));
		superAdminAllowedPath.put("/api/v1/shop-admin/review-visibility/title", List.of(HttpMethod.GET));
		superAdminAllowedPath.put("/api/v1/shop-admin/review-design/theme-list", List.of(HttpMethod.GET));
		superAdminAllowedPath.put("/api/v1/logout", List.of(HttpMethod.POST));
		return superAdminAllowedPath;
	}
}
