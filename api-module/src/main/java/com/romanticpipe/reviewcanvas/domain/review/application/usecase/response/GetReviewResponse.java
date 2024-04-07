package com.romanticpipe.reviewcanvas.domain.review.application.usecase.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

import com.romanticpipe.reviewcanvas.domain.Review;

@Schema(name = "GetReviewResponse", description = "리뷰 조회 응답")
public record GetReviewResponse(@Schema(description = "리뷰 id", requiredMode = Schema.RequiredMode.REQUIRED)
								Long reviewId,
								@Schema(description = "리뷰 설명", requiredMode = Schema.RequiredMode.REQUIRED)
								String content,
								@Schema(description = "리뷰 점수", requiredMode = Schema.RequiredMode.REQUIRED)
								int score) {

	public GetReviewResponse {
		Objects.requireNonNull(reviewId);
		Objects.requireNonNull(content);
		if (score < 1 || score > 5) {
			throw new IllegalArgumentException("리뷰 점수는 1~5 사이의 값이어야 합니다.");
		}
	}

	public static GetReviewResponse from(Review review) {
		return new GetReviewResponse(review.getId(), review.getContent(), review.getScore());
	}
}
