package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.AlignmentPosition;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Border;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Font;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Padding;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateReviewTitleRequest(
	@Schema(description = "리뷰 제목", requiredMode = Schema.RequiredMode.REQUIRED)
	String title,
	@Schema(description = "리뷰 제목 정렬 위치", requiredMode = Schema.RequiredMode.REQUIRED)
	AlignmentPosition titleAlignmentPosition,
	@Schema(description = "리뷰 제목 패딩", requiredMode = Schema.RequiredMode.REQUIRED)
	Padding titlePadding,
	@Schema(description = "리뷰 제목 폰트", requiredMode = Schema.RequiredMode.REQUIRED)
	Font titleFont,
	@Schema(description = "리뷰 제목 테두리", requiredMode = Schema.RequiredMode.REQUIRED)
	Border titleBorder,
	@Schema(description = "리뷰 제목 테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String titleBorderColor,
	@Schema(description = "리뷰 제목 배경 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String titleBackGround,
	@Schema(description = "리뷰 설명글", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	String description,
	@Schema(description = "리뷰 설명글 정렬 위치", requiredMode = Schema.RequiredMode.REQUIRED)
	AlignmentPosition descriptionAlignmentPosition,
	@Schema(description = "리뷰 설명글 패딩", requiredMode = Schema.RequiredMode.REQUIRED)
	Padding descriptionPadding,
	@Schema(description = "리뷰 설명글 폰트", requiredMode = Schema.RequiredMode.REQUIRED)
	Font descriptionFont,
	@Schema(description = "리뷰 설명글 테두리", requiredMode = Schema.RequiredMode.REQUIRED)
	Border descriptionBorder,
	@Schema(description = "리뷰 설명글 테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String descriptionBorderColor,
	@Schema(description = "리뷰 설명글 배경 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String descriptionBackGround
) {

}
