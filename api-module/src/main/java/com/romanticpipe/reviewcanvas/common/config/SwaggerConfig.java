package com.romanticpipe.reviewcanvas.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
	info = @Info(title = "리뷰캔버스 API 문서",
		description = "[Regacy] 리뷰캔버스 프로젝트의 API 문서입니다.",
		version = "v1"),
	servers = {
//		@Server(url = "https://review-canvas-dev.com", description = "테스트 서버"),
		@Server(url = "https://api.review-canvas.com", description = "운영 서버")
	}
)
@Configuration
@SecurityScheme(
	name = "Bearer Authentication",
	type = SecuritySchemeType.HTTP,
	in = SecuritySchemeIn.HEADER,
	bearerFormat = "JWT",
	scheme = "bearer"
)
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi v1GroupedOpenApi() {
		String[] paths = {"/api/v1/**"};

		return GroupedOpenApi
			.builder()
			.group("API v1")
			.pathsToMatch(paths)
			.build();
	}

	@Bean
	public GroupedOpenApi v2GroupedOpenApi() {
		String[] paths = {"/api/v2/**"};

		return GroupedOpenApi
			.builder()
			.group("API v2")
			.pathsToMatch(paths)
			.build();
	}

}
