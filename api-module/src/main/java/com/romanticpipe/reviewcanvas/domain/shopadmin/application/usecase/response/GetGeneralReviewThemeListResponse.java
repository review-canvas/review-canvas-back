package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response;

import java.util.Objects;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignPosition;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "GetGeneralReviewThemeListResponse", description = "기본 리뷰 테마 리스트 조회 응답")
public record GetGeneralReviewThemeListResponse(
	@Schema(description = "Review Design id", requiredMode = Schema.RequiredMode.REQUIRED)
	Long reviewDesignId,
	@Schema(description = "Review Design Position", requiredMode = Schema.RequiredMode.REQUIRED)
	ReviewDesignPosition reviewDesignPosition,
	@Schema(description = "Review Design Url", requiredMode = Schema.RequiredMode.REQUIRED)
	String reviewDesignUrl) {

	public GetGeneralReviewThemeListResponse {
		Objects.requireNonNull(reviewDesignId);
		Objects.requireNonNull(reviewDesignPosition);
		Objects.requireNonNull(reviewDesignUrl);
	}

	public static GetGeneralReviewThemeListResponse from(ReviewDesign reviewDesign) {
		return new GetGeneralReviewThemeListResponse(reviewDesign.getId(), reviewDesign.getReviewDesignPosition(),
			reviewDesign.getReviewDesignUrl());
	}
}
