package com.romanticpipe.reviewcanvas.domain.review.application.usecase.response;

import java.util.Objects;

import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.domain.ReviewStatus;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetAwaitReviewResponse(@Schema(description = "리뷰 id", requiredMode = Schema.RequiredMode.REQUIRED)
									 Long reviewId,
									 @Schema(description = "리뷰 설명", requiredMode = Schema.RequiredMode.REQUIRED)
									 String content,
									 @Schema(description = "리뷰 점수", requiredMode = Schema.RequiredMode.REQUIRED)
									 int score,
									 @Schema(description = "리뷰 상태", requiredMode = Schema.RequiredMode.REQUIRED)
									 ReviewStatus reviewStatus) {

	public GetAwaitReviewResponse {
		Objects.requireNonNull(reviewId);
		Objects.requireNonNull(content);
		if (score < 1 || score > 5) {
			throw new IllegalArgumentException("리뷰 점수는 1~5 사이의 값이어야 합니다.");
		}
	}

	public static GetAwaitReviewResponse from(Review review) {
		return new GetAwaitReviewResponse(review.getId(), review.getContent(), review.getScore(), review.getStatus());
	}
}
