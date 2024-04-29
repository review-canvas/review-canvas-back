package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignPosition;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "적용 가능한 리뷰 디자인 조회 response")
public record GetApplicableReviewDesignResponse(
	@Schema(description = "리뷰 디자인 ID", requiredMode = Schema.RequiredMode.REQUIRED)
	Integer reviewDesignId,
	@Schema(description = "리뷰 테마 type", requiredMode = Schema.RequiredMode.REQUIRED)
	ReviewDesignType reviewDesignType,
	@Schema(description = "리뷰 표시 위치", requiredMode = Schema.RequiredMode.REQUIRED)
	ReviewDesignPosition reviewDesignPosition,
	@Schema(description = "리뷰 테마 이름", requiredMode = Schema.RequiredMode.REQUIRED)
	String themeName,
	@Schema(description = "리뷰 레이아웃 type", requiredMode = Schema.RequiredMode.REQUIRED)
	String layoutType,
	String padding,
	String gap,
	String boxShadowColor,
	int boxShadowWidth,
	String borderColor,
	int borderTransparency,
	int borderWidth,
	String pagingType,
	int pagingNumber,
	String textAlign,
	String pointColor,
	String pointType,
	int lineEllipsis,
	String reviewDesignUrl
) {

	public static GetApplicableReviewDesignResponse from(ReviewDesign reviewDesign) {
		return GetApplicableReviewDesignResponse.builder()
			.reviewDesignId(reviewDesign.getId())
			.reviewDesignType(reviewDesign.getReviewDesignType())
			.reviewDesignPosition(reviewDesign.getReviewDesignPosition())
			.themeName(reviewDesign.getThemeName())
			.layoutType(reviewDesign.getLayoutType())
			.padding(reviewDesign.getPadding())
			.gap(reviewDesign.getGap())
			.boxShadowColor(reviewDesign.getBoxShadowColor())
			.boxShadowWidth(reviewDesign.getBoxShadowWidth())
			.borderColor(reviewDesign.getBorderColor())
			.borderTransparency(reviewDesign.getBorderTransparency())
			.borderWidth(reviewDesign.getBorderWidth())
			.pagingType(reviewDesign.getPagingType())
			.pagingNumber(reviewDesign.getPagingNumber())
			.textAlign(reviewDesign.getTextAlign())
			.pointColor(reviewDesign.getPointColor())
			.pointType(reviewDesign.getPointType())
			.lineEllipsis(reviewDesign.getLineEllipsis())
			.reviewDesignUrl(reviewDesign.getReviewDesignUrl())
			.build();
	}
}

