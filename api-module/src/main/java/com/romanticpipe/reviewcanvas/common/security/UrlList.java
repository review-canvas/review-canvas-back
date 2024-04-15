package com.romanticpipe.reviewcanvas.common.security;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Getter
public class UrlList {
	private List<String> publicUrls = Arrays.asList(
		"/",
		"/api/v1/shop-admin/login",
		"/api/v1/shop-admin/sign-up",
		"/api/v1/super-admin/login",
		"api/v1/admin/auth",
		"/swagger-ui/**",
		"/v3/**"
	);

	private List<String> superUrls = Arrays.asList();
}
