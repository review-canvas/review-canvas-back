package com.romanticpipe.reviewcanvas.common.security;

import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

@Component
public final class IgnoredPathList {

	@Getter
	private final MultiValueMap<String, HttpMethod> allIgnoredPath;
	@Getter
	private final MultiValueMap<String, HttpMethod> superAdminIgnoredPath;

	private final List<HttpMethod> allMethod =
		Arrays.asList(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE);

	private IgnoredPathList() {
		allIgnoredPath = initAllIgnoredPath();
		superAdminIgnoredPath = initSuperAdminIgnoredPath();
	}

	private MultiValueMap<String, HttpMethod> initAllIgnoredPath() {
		MultiValueMap<String, HttpMethod> allIgnoredPath = new LinkedMultiValueMap<>();
		allIgnoredPath.put("/swagger-ui/**", allMethod);
		allIgnoredPath.put("/v3/api-docs/**", allMethod);
		allIgnoredPath.put("/api/v1/shop-admin/login", List.of(HttpMethod.POST));
		allIgnoredPath.put("/api/v1/super-admin/login", List.of(HttpMethod.POST));
		allIgnoredPath.put("/api/v1/reissue-access-token", List.of(HttpMethod.POST));
		allIgnoredPath.put("/api/v1/shop-admin/sign-up", List.of(HttpMethod.POST));
		return allIgnoredPath;
	}

	private MultiValueMap<String, HttpMethod> initSuperAdminIgnoredPath() {
		return new LinkedMultiValueMap<>();
	}
}
