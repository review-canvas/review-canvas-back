package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLike;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.ButtonType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ReviewLikeResponse(
	@Schema(description = "리뷰 좋아요 버튼 타입", requiredMode = Schema.RequiredMode.REQUIRED)
	ButtonType buttonType,
	@Schema(description = "아이콘 색상", requiredMode = Schema.RequiredMode.REQUIRED, example = "#FFFFFF")
	String iconColor,
	@Schema(description = "텍스트 색상", requiredMode = Schema.RequiredMode.REQUIRED, example = "#3F21DB")
	String textColor,
	@Schema(description = "버튼 테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED, example = "#3F21DB")
	String buttonBorderColor,
	@Schema(description = "버튼 라운드", requiredMode = Schema.RequiredMode.REQUIRED)
	RoundResponse buttonRound
) {
	public static ReviewLikeResponse from(ReviewLike reviewLike) {
		return ReviewLikeResponse.builder()
			.buttonType(reviewLike.getButtonType())
			.iconColor(reviewLike.getIconColor())
			.textColor(reviewLike.getTextColor())
			.buttonBorderColor(reviewLike.getButtonBorderColor())
			.buttonRound(RoundResponse.from(reviewLike))
			.build();
	}

	public ReviewLike toVO() {
		return ReviewLike.builder()
			.buttonType(buttonType)
			.iconColor(iconColor)
			.textColor(textColor)
			.buttonBorderColor(buttonBorderColor)
			.buttonRoundTopLeft(buttonRound.topLeft())
			.buttonRoundTopRight(buttonRound.topRight())
			.buttonRoundBottomLeft(buttonRound.bottomLeft())
			.buttonRoundBottomRight(buttonRound.bottomRight())
			.build();
	}
}
