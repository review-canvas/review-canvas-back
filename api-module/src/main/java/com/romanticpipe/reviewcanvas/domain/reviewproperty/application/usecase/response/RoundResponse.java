package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLike;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Round;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record RoundResponse(
	@Schema(description = "라운드 상단 좌측", requiredMode = Schema.RequiredMode.REQUIRED)
	String topLeft,
	@Schema(description = "라운드 상단 우측", requiredMode = Schema.RequiredMode.REQUIRED)
	String topRight,
	@Schema(description = "라운드 하단 좌측", requiredMode = Schema.RequiredMode.REQUIRED)
	String bottomLeft,
	@Schema(description = "라운드 하단 우측", requiredMode = Schema.RequiredMode.REQUIRED)
	String bottomRight
) {
	public static RoundResponse from(Round round) {
		return RoundResponse.builder()
			.topLeft(round.getTopLeft())
			.topRight(round.getTopRight())
			.bottomLeft(round.getBottomLeft())
			.bottomRight(round.getBottomRight())
			.build();
	}


	public static RoundResponse from(ReviewLike reviewLike) {
		return RoundResponse.builder()
			.topLeft(reviewLike.getButtonRoundTopLeft())
			.topRight(reviewLike.getButtonRoundTopRight())
			.bottomLeft(reviewLike.getButtonRoundBottomLeft())
			.bottomRight(reviewLike.getButtonRoundBottomRight())
			.build();
	}

	public Round toVO() {
		return new Round(topLeft, topRight, bottomLeft, bottomRight);
	}
}
