package com.romanticpipe.reviewcanvas.domain.auth.application.usecase.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "AccessToken 재발급 요청 dto")
public record ReissueAccessTokenRequest(
	@Schema(description = "우리 api 서버에서 발급한 RefreshToken", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String refreshToken
) {
}
