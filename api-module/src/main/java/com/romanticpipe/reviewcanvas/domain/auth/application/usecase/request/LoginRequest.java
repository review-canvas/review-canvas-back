package com.romanticpipe.reviewcanvas.domain.auth.application.usecase.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "LoginRequest", description = "Admin 로그인 요청")
public record LoginRequest(@Schema(description = "Email", requiredMode = Schema.RequiredMode.REQUIRED)
						   @NotBlank String email,
						   @Schema(description = "Password", requiredMode = Schema.RequiredMode.REQUIRED)
						   @NotBlank String password) {

}
