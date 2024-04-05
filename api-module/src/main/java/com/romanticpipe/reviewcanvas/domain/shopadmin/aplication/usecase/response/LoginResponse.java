package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response;

import java.util.Objects;

import com.romanticpipe.reviewcanvas.domain.ShopAdmin;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LoginResponse", description = "Shop Admin 로그인 응답")
public record LoginResponse(@Schema(description = "Shop Admin id", requiredMode = Schema.RequiredMode.REQUIRED)
							Long shopAdminId,
							@Schema(description = "Access Token", requiredMode = Schema.RequiredMode.REQUIRED)
							String accessToken,
							@Schema(description = "refresh Token", requiredMode = Schema.RequiredMode.REQUIRED)
							String refreshToken) {

	public LoginResponse {
		Objects.requireNonNull(shopAdminId);
		Objects.requireNonNull(accessToken);
		Objects.requireNonNull(refreshToken);
	}
	
	public static LoginResponse from(ShopAdmin shopAdmin, TokenInfoResponse tokenInfoResponse) {
		return new LoginResponse(shopAdmin.getId(), tokenInfoResponse.getAccessToken(),
			tokenInfoResponse.getRefreshToken());
	}
}
