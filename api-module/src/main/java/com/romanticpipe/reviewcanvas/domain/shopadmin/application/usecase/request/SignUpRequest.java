package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Schema(name = "SignUpRequest", description = "Shop Admin 회원가입 요청")
public record SignUpRequest(@Schema(description = "이메일", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotBlank String email,
							@Schema(description = "비밀번호", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotBlank String password,
							@Schema(description = "전화번호", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotBlank String phoneNumber,
							@Schema(description = "상호명", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotBlank String mallName,
							@Schema(description = "Mall ID", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotBlank String mallId,
							@Schema(description = "동의한 약관 id list", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotEmpty List<Integer> consentedTermsIds
) {

}
