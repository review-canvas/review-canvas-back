package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request;

import com.romanticpipe.reviewcanvas.domain.ShopInstallType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(name = "SignUpRequest", description = "Shop Admin 회원가입 요청")
public record SignUpRequest(@Schema(description = "Email", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotBlank String email,
							@Schema(description = "Password", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotBlank String password,
							@Schema(description = "Name", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotBlank String name,
							@Schema(description = "Mall Number", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotBlank String mallNumber,
							@Schema(description = "Phone Number", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotBlank String phoneNumber,
							@Schema(description = "Review Title Active", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotNull Boolean title,
							@Schema(description = "Review Author Active", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotNull Boolean author,
							@Schema(description = "Review Point Active", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotNull Boolean point,
							@Schema(description = "Review Media Active", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotNull Boolean media,
							@Schema(description = "Review Content Active", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotNull Boolean content,
							@Schema(description = "Review CreateAt Active", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotNull Boolean createdAt,
							@Schema(description = "Review UpdateAt Active", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotNull Boolean updatedAt,
							@Schema(description = "Review Design Id", requiredMode = Schema.RequiredMode.REQUIRED)
							@NotNull Long reviewDesignId,
							@Schema(description = "설치 유형(직접 설치, 설치 의뢰)", requiredMode = Schema.RequiredMode.REQUIRED)
							ShopInstallType shopInstallType,
							@Schema(description = "설치 의뢰 시, 요청 사항", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
							String installRequirement
) {

}
