package com.romanticpipe.reviewcanvas.common.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class UrlList {
	private List<String> publicUrls = Arrays.asList(
		"/",
		"/api/v1/shopadmin/login",
		"/api/v1/shopadmin/signup",
		"/api/v1/superadmin/login",
		"/swagger-ui/**",
		"/v3/**"
	);
}
