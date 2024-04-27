package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignPosition;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignType;

public class GetApplicableReviewDesignResponse {

	private Integer id;
	private Integer shopAdminId;
	private ReviewDesignType reviewDesignType;
	private ReviewDesignPosition reviewDesignPosition;
	private String themeName;
	private String layoutType;
	private String padding;
	private String gap;
	private String boxShadowColor;
	private int boxShadowWidth;
	private String borderColor;
	private int borderTransparency;
	private int borderWidth;
	private String pagingType;
	private int pagingNumber;
	private String textAlign;
	private String pointColor;
	private String pointType;
	private int lineEllipsis;
	private String reviewDesignUrl;

	public GetApplicableReviewDesignResponse(ReviewDesign reviewDesign) {
		this.id = reviewDesign.getId();
		this.shopAdminId = reviewDesign.getShopAdminId();
		this.reviewDesignType = reviewDesign.getReviewDesignType();
		this.reviewDesignPosition = reviewDesign.getReviewDesignPosition();
		this.themeName = reviewDesign.getThemeName();
		this.layoutType = reviewDesign.getLayoutType();
		this.padding = reviewDesign.getPadding();
		this.gap = reviewDesign.getGap();
		this.boxShadowColor = reviewDesign.getBoxShadowColor();
		this.boxShadowWidth = reviewDesign.getBoxShadowWidth();
		this.borderColor = reviewDesign.getBorderColor();
		this.borderTransparency = reviewDesign.getBorderTransparency();
		this.borderWidth = reviewDesign.getBorderWidth();
		this.pagingType = reviewDesign.getPagingType();
		this.pagingNumber = reviewDesign.getPagingNumber();
		this.textAlign = reviewDesign.getTextAlign();
		this.pointColor = reviewDesign.getPointColor();
		this.pointType = reviewDesign.getPointType();
		this.lineEllipsis = reviewDesign.getLineEllipsis();
		this.reviewDesignUrl = reviewDesign.getReviewDesignUrl();
	}
}
