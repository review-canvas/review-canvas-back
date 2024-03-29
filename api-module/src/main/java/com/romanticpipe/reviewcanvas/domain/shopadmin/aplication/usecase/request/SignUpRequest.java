package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SignUpRequest", description = "Shop Admin 회원가입 요청")
public record SignUpRequest(@Schema(description = "Email", requiredMode = Schema.RequiredMode.REQUIRED)
							String email,
							@Schema(description = "Password", requiredMode = Schema.RequiredMode.REQUIRED)
							String password) {

	public SignUpRequest {
		Objects.requireNonNull(email);
		Objects.requireNonNull(password);
	}
}
