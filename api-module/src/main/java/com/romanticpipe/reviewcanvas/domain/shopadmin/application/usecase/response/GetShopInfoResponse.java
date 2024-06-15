package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response;

import java.time.LocalDateTime;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.ReviewLayoutDesign;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@Schema(name = "GetShopInfoResponse", description = "샵 조회 response")
public record GetShopInfoResponse(
	@Schema(description = "샵 id", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String mallId,
	@Schema(description = "샵 가입 날짜", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull LocalDateTime createdAt,
	@Schema(description = "샵 이름", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String mallName,
	@Schema(description = "샵 전화번호", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String mallNumber,
	@Schema(description = "활성화 여부", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull Boolean approveStatus,
	@Schema(description = "리뷰 갯수", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull Integer reviewsAmount,
	@Schema(description = "리뷰 레이아웃 디자인", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid ReviewLayoutDesign reviewLayoutDesign
) {
}
