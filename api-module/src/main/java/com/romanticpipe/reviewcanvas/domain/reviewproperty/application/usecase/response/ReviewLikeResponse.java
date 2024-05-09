package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ButtonType;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLike;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ReviewLikeResponse(
	@Schema(description = "리뷰 좋아요 버튼 타입", requiredMode = Schema.RequiredMode.REQUIRED,
		allowableValues = {"NONE", "THUMB_UP_WITH_TEXT", "THUMB_UP"})
	ButtonType buttonType,
	@Schema(description = "아이콘 색상", requiredMode = Schema.RequiredMode.REQUIRED, example = "#FFFFFF")
	String iconColor,
	@Schema(description = "텍스트 색상", requiredMode = Schema.RequiredMode.REQUIRED, example = "#3F21DB")
	String textColor,
	@Schema(description = "버튼 테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED, example = "#3F21DB")
	String buttonBorderColor,
	@Schema(description = "리뷰 좋아요 버튼 라운드 상단 좌측", requiredMode = Schema.RequiredMode.REQUIRED)
	String buttonRoundTopLeft,
	@Schema(description = "리뷰 좋아요 버튼 라운드 상단 우측", requiredMode = Schema.RequiredMode.REQUIRED)
	String buttonRoundTopRight,
	@Schema(description = "리뷰 좋아요 버튼 라운드 하단 좌측", requiredMode = Schema.RequiredMode.REQUIRED)
	String buttonRoundBottomLeft,
	@Schema(description = "리뷰 좋아요 버튼 라운드 하단 우측", requiredMode = Schema.RequiredMode.REQUIRED)
	String buttonRoundBottomRight
) {
	public static ReviewLikeResponse from(ReviewLike reviewLike) {
		return ReviewLikeResponse.builder()
			.buttonType(reviewLike.getButtonType())
			.iconColor(reviewLike.getIconColor())
			.textColor(reviewLike.getTextColor())
			.buttonBorderColor(reviewLike.getButtonBorderColor())
			.buttonRoundTopLeft(reviewLike.getButtonRoundTopLeft())
			.buttonRoundTopRight(reviewLike.getButtonRoundTopRight())
			.buttonRoundBottomLeft(reviewLike.getButtonRoundBottomLeft())
			.buttonRoundBottomRight(reviewLike.getButtonRoundBottomRight())
			.build();
	}
}
