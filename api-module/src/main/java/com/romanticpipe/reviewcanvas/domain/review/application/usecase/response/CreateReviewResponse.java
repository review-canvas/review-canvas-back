package com.romanticpipe.reviewcanvas.domain.review.application.usecase.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CreateReviewResponse", description = "리뷰 생성 응답")
public record CreateReviewResponse(@Schema(description = "리뷰 상품 id", requiredMode = Schema.RequiredMode.REQUIRED)
								   String productId,
								   @Schema(description = "리뷰 평점", requiredMode = Schema.RequiredMode.REQUIRED)
								   int score,
								   @Schema(description = "리뷰 내용", requiredMode = Schema.RequiredMode.REQUIRED)
								   String content) {

}

