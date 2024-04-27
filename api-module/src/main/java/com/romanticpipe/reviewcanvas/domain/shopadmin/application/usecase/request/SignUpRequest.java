package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "SignUpRequest", description = "Shop Admin 회원가입 요청")
public record SignUpRequest(@Schema(description = "Email", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotBlank String email,
							@Schema(description = "Password", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotBlank String password,
							@Schema(description = "Phone Number", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotBlank String phoneNumber,
							@Schema(description = "Mall Name", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotBlank String mallName
) {

}
