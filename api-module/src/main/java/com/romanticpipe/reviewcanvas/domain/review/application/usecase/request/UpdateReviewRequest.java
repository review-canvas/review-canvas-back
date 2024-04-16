package com.romanticpipe.reviewcanvas.domain.review.application.usecase.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UpdateReviewRequest", description = "리뷰 수정 요청")
public record UpdateReviewRequest(
	@Schema(description = "리뷰 설명", requiredMode = Schema.RequiredMode.REQUIRED)
	String content,
	@Schema(description = "리뷰 점수", requiredMode = Schema.RequiredMode.REQUIRED)
	int score) {

}
