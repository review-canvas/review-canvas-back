package com.romanticpipe.reviewcanvas.domain.review.application.usecase.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CreateReviewRequest", description = "리뷰 생성 요청")
public record CreateReviewRequest(@Schema(description = "리뷰 점수", requiredMode = Schema.RequiredMode.REQUIRED)
								  int score,
								  @Schema(description = "리뷰 내용", requiredMode = Schema.RequiredMode.REQUIRED)
								  String content) {
}
