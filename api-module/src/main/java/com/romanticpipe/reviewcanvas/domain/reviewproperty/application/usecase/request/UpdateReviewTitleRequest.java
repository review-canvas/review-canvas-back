package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.AlignmentPosition;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateReviewTitleRequest(
	@Schema(description = "리뷰 제목", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String title,
	@Schema(description = "리뷰 제목 정렬 위치", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull AlignmentPosition titleAlignmentPosition,
	@Schema(description = "리뷰 제목 패딩", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid PaddingRequest titlePadding,
	@Schema(description = "리뷰 제목 폰트", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid FontRequest titleFont,
	@Schema(description = "리뷰 제목 테두리", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid BorderRequest titleBorder,
	@Schema(description = "리뷰 제목 테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String titleBorderColor,
	@Schema(description = "리뷰 제목 배경 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String titleBackGround,
	@Schema(description = "리뷰 설명글", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@NotBlank String description,
	@Schema(description = "리뷰 설명글 정렬 위치", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull AlignmentPosition descriptionAlignmentPosition,
	@Schema(description = "리뷰 설명글 패딩", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid PaddingRequest descriptionPadding,
	@Schema(description = "리뷰 설명글 폰트", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid FontRequest descriptionFont,
	@Schema(description = "리뷰 설명글 테두리", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid BorderRequest descriptionBorder,
	@Schema(description = "리뷰 설명글 테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String descriptionBorderColor,
	@Schema(description = "리뷰 설명글 배경 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String descriptionBackGround
) {

}
