package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(name = "LoginResponse", description = "Shop Admin 로그인 응답")
public record CheckLoginResponse(@Schema(description = "Admin id", requiredMode = Schema.RequiredMode.REQUIRED)
								 Long adminId,
								 @Schema(description = "Role", requiredMode = Schema.RequiredMode.REQUIRED)
								 String role) {

	public CheckLoginResponse {
		Objects.requireNonNull(adminId);
		Objects.requireNonNull(role);
	}

	public static CheckLoginResponse from(Long id, String role) {
		return new CheckLoginResponse(id, role);
	}
}
