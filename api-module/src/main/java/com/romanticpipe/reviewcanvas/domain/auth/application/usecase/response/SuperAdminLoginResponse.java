package com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(name = "SuperAdminLoginResponse", description = "Super Admin 로그인 응답")
public record SuperAdminLoginResponse(
	@Schema(description = "Super Admin id", requiredMode = Schema.RequiredMode.REQUIRED)
	Integer shopAdminId,
	@Schema(description = "Access Token", requiredMode = Schema.RequiredMode.REQUIRED)
	String accessToken,
	@Schema(description = "Refresh Token", requiredMode = Schema.RequiredMode.REQUIRED)
	String refreshToken
) {
	public SuperAdminLoginResponse {
		Objects.requireNonNull(shopAdminId);
		Objects.requireNonNull(accessToken);
		Objects.requireNonNull(refreshToken);
	}
}

