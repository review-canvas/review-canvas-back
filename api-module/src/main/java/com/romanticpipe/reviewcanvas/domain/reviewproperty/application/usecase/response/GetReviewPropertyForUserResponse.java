package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewContainer;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewDesignView;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewTitle;
import io.swagger.v3.oas.annotations.media.Schema;

public record GetReviewPropertyForUserResponse(
	@Schema(description = "레이아웃 속성", requiredMode = Schema.RequiredMode.REQUIRED)
	GetReviewLayoutResponse reviewLayout,
	@Schema(description = "컨테이너 속성", requiredMode = Schema.RequiredMode.REQUIRED)
	GetReviewContainerResponse reviewContainer,
	@Schema(description = "제목 속성", requiredMode = Schema.RequiredMode.REQUIRED)
	GetReviewTitleResponse reviewTitle,
	@Schema(description = "컬럼 속성", requiredMode = Schema.RequiredMode.REQUIRED)
	GetReviewColumnResponse reviewColumn,
	@Schema(description = "리뷰 디자인뷰 속성", requiredMode = Schema.RequiredMode.REQUIRED)
	GetReviewDesignViewResponse reviewDesignView
) {
	public static GetReviewPropertyForUserResponse from(ReviewLayout reviewLayout, ReviewContainer reviewContainer,
														ReviewTitle reviewTitle, ReviewTitle reviewDescription,
														ReviewColumn reviewColumn, ReviewDesignView reviewDesignView) {
		return new GetReviewPropertyForUserResponse(
			GetReviewLayoutResponse.from(reviewLayout),
			GetReviewContainerResponse.from(reviewContainer),
			GetReviewTitleResponse.from(reviewTitle, reviewDescription),
			GetReviewColumnResponse.from(reviewColumn),
			GetReviewDesignViewResponse.from(reviewDesignView)
		);
	}
}
