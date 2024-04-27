package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignPosition;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignType;

public record GetApplicableReviewDesignResponse(
	Integer id,
	Integer shopAdminId,
	ReviewDesignType reviewDesignType,
	ReviewDesignPosition reviewDesignPosition,
	String themeName,
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
		return new GetApplicableReviewDesignResponse(
			reviewDesign.getId(),
			reviewDesign.getShopAdminId(),
			reviewDesign.getReviewDesignType(),
			reviewDesign.getReviewDesignPosition(),
			reviewDesign.getThemeName(),
			reviewDesign.getLayoutType(),
			reviewDesign.getPadding(),
			reviewDesign.getGap(),
			reviewDesign.getBoxShadowColor(),
			reviewDesign.getBoxShadowWidth(),
			reviewDesign.getBorderColor(),
			reviewDesign.getBorderTransparency(),
			reviewDesign.getBorderWidth(),
			reviewDesign.getPagingType(),
			reviewDesign.getPagingNumber(),
			reviewDesign.getTextAlign(),
			reviewDesign.getPointColor(),
			reviewDesign.getPointType(),
			reviewDesign.getLineEllipsis(),
			reviewDesign.getReviewDesignUrl()
		);
	}
}

