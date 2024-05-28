package com.romanticpipe.reviewcanvas.domain.review.application.usecase.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "CreateReviewLikeRequest", description = "좋아요 생성 요청")
public record CreateReviewLikeRequest(
	@Schema(description = "좋아요를 누를 shop의 mall id", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull String mallId,
	@Schema(description = "좋아요를 누를 유저의 member id", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull String memberId
) {

}
