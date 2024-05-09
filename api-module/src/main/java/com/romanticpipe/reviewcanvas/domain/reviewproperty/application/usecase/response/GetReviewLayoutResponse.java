package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.FocusAreaLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.ImageReviewAreaLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.ReviewLayoutDesign;
import io.swagger.v3.oas.annotations.media.Schema;

public record GetReviewLayoutResponse(@Schema(description = "베스트 리뷰 영역", requiredMode = Schema.RequiredMode.REQUIRED)
									  Boolean bestReviewAreaActivation,
									  @Schema(description = "리뷰 통계 영역", requiredMode = Schema.RequiredMode.REQUIRED)
									  Boolean reviewStatisticsAreaActivation,
									  @Schema(description = "이미지/동영상 영역", requiredMode = Schema.RequiredMode.REQUIRED)
									  Boolean imageReviewAreaActivation,
									  @Schema(description = "포커스 영역 레이아웃", requiredMode = Schema.RequiredMode.REQUIRED)
									  FocusAreaLayout focusAreaLayout,
									  @Schema(description = "이미지/동영상,리뷰 영역 레이아웃",
										  requiredMode = Schema.RequiredMode.REQUIRED)
									  ImageReviewAreaLayout imageReviewAreaLayout,
									  @Schema(description = "리뷰 레이아웃 디자인", requiredMode = Schema.RequiredMode.REQUIRED)
									  ReviewLayoutDesign reviewLayoutDesign) {

	public static GetReviewLayoutResponse from(ReviewLayout reviewLayout) {
		return new GetReviewLayoutResponse(reviewLayout.getBestReviewAreaActivation(),
			reviewLayout.getImageReviewAreaActivation(),
			reviewLayout.getReviewStatisticsAreaActivation(),
			reviewLayout.getFocusAreaLayout(),
			reviewLayout.getImageReviewAreaLayout(),
			reviewLayout.getReviewLayoutDesign());
	}
}
