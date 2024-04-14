package com.romanticpipe.reviewcanvas.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
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
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi allOpenApi() {
		String[] paths = {"/**"};

		return GroupedOpenApi
			.builder()
			.group("모든 API")
			.pathsToMatch(paths)
			.build();
	}

}
