package com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(name = "ShopAdminLoginResponse", description = "Shop Admin 로그인 응답")
public record ShopAdminLoginResponse(@Schema(description = "Shop Admin id", requiredMode = Schema.RequiredMode.REQUIRED)
									 Integer shopAdminId,
									 @Schema(description = "Access Token", requiredMode = Schema.RequiredMode.REQUIRED)
									 String accessToken,
									 @Schema(description = "Refresh Token", requiredMode = Schema.RequiredMode.REQUIRED)
									 String refreshToken) {

	public ShopAdminLoginResponse {
		Objects.requireNonNull(shopAdminId);
		Objects.requireNonNull(accessToken);
		Objects.requireNonNull(refreshToken);
	}
}
