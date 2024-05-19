package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLike;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.ButtonType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewLikeRequest(
	@Schema(description = "리뷰 좋아요 버튼 타입", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull ButtonType buttonType,
	@Schema(description = "아이콘 색상", requiredMode = Schema.RequiredMode.REQUIRED, example = "#FFFFFF")
	@NotBlank String iconColor,
	@Schema(description = "텍스트 색상", requiredMode = Schema.RequiredMode.REQUIRED, example = "#3F21DB")
	@NotBlank String textColor,
	@Schema(description = "버튼 테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED, example = "#3F21DB")
	@NotBlank String buttonBorderColor,
	@Schema(description = "버튼 라운드", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid RoundRequest buttonRound
) {

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
