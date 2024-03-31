package com.romanticpipe.reviewcanvas.domain.review.application.usecase.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UpdateReviewResponse", description = "리뷰 수정 응답")
public record UpdateReviewResponse(@Schema(description = "리뷰 id", requiredMode = Schema.RequiredMode.REQUIRED)
								   Long reviewId,
								   @Schema(description = "리뷰 설명", requiredMode = Schema.RequiredMode.REQUIRED)
								   String content,
								   @Schema(description = "리뷰 점수", requiredMode = Schema.RequiredMode.REQUIRED)
								   int score) {

}
