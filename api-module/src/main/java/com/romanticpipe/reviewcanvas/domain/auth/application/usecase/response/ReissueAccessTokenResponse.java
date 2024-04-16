package com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(description = "AccessToken 재발급 응답")
public record ReissueAccessTokenResponse(
	@Schema(description = "재발급된 Access Token", requiredMode = Schema.RequiredMode.REQUIRED)
	String accessToken) {

	public ReissueAccessTokenResponse {
		Objects.requireNonNull(accessToken);
	}
}
