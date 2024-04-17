package com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(name = "LoginResponse", description = "Admin 로그인 응답")
public record LoginResponse(@Schema(description = "Admin id", requiredMode = Schema.RequiredMode.REQUIRED)
							Long adminId,
							@Schema(description = "Access Token", requiredMode = Schema.RequiredMode.REQUIRED)
							String accessToken,
							@Schema(description = "Refresh Token", requiredMode = Schema.RequiredMode.REQUIRED)
							String refreshToken) {

	public LoginResponse {
		Objects.requireNonNull(adminId);
		Objects.requireNonNull(accessToken);
		Objects.requireNonNull(refreshToken);
	}
}
