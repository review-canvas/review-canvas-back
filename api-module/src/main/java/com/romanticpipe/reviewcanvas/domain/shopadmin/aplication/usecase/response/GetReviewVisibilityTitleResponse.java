package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response;

import java.util.Objects;

import com.romanticpipe.reviewcanvas.domain.ReviewVisibility;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "GetReviewVisibilityTitleResponse", description = "리뷰 노출 항목 title 조회 응답")
public record GetReviewVisibilityTitleResponse(
	@Schema(description = "Review Title Active", requiredMode = Schema.RequiredMode.REQUIRED)
	String title) {

	public GetReviewVisibilityTitleResponse {
		Objects.requireNonNull(title);
	}

	public static GetReviewVisibilityTitleResponse from(ReviewVisibility reviewVisibility) {
		return new GetReviewVisibilityTitleResponse(reviewVisibility.getClass().getFields().toString());
	}
}
