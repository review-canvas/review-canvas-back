package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LoginResponse", description = "Shop Admin 로그인 응답")
public record LoginResponse(@Schema(description = "Shop Admin id", requiredMode = Schema.RequiredMode.REQUIRED)
							Long shopAdminId,
							@Schema(description = "Access Token", requiredMode = Schema.RequiredMode.REQUIRED)
							String accessToken) {

	public LoginResponse {
		Objects.requireNonNull(shopAdminId);
		Objects.requireNonNull(accessToken);
	}

	public static LoginResponse from(Long id, String accessToken) {
		return new LoginResponse(id, accessToken);
	}
}
