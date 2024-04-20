package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response;

import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "GetReviewVisibilityTitleResponse", description = "리뷰 노출 항목 title 조회 응답")
public record GetReviewVisibilityTitleResponse(
	@Schema(description = "Review Title Active", requiredMode = Schema.RequiredMode.REQUIRED)
	List<String> titles) {

	public GetReviewVisibilityTitleResponse {
		Objects.requireNonNull(titles);
	}

	public static GetReviewVisibilityTitleResponse from(List<String> titles) {
		return new GetReviewVisibilityTitleResponse(titles);
	}
}
