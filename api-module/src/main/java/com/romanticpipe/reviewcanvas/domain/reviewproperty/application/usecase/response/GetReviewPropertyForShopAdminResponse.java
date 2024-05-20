package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewContainer;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewTitle;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "GetReviewPropertyResponse", description = "ShopAdmin 리뷰 속성 조회 응답")
public record GetReviewPropertyForShopAdminResponse(
	@Schema(description = "레이아웃 속성", requiredMode = Schema.RequiredMode.REQUIRED)
	ReviewLayout reviewLayout,
	@Schema(description = "컨테이너 속성", requiredMode = Schema.RequiredMode.REQUIRED)
	ReviewContainer reviewContainer,
	@Schema(description = "제목 속성", requiredMode = Schema.RequiredMode.REQUIRED)
	ReviewTitle reviewTitle,
	@Schema(description = "컬럼 속성", requiredMode = Schema.RequiredMode.REQUIRED)
	ReviewColumn reviewColumn
) {

	public static GetReviewPropertyForShopAdminResponse from(ReviewLayout reviewLayout, ReviewContainer reviewContainer,
															 ReviewTitle reviewTitle, ReviewColumn reviewColumn) {
		return new GetReviewPropertyForShopAdminResponse(reviewLayout, reviewContainer, reviewTitle, reviewColumn);
	}
}
