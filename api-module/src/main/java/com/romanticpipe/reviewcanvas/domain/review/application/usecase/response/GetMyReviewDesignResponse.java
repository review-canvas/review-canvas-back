package com.romanticpipe.reviewcanvas.domain.review.application.usecase.response;

import com.romanticpipe.reviewcanvas.domain.MyReviewDesign;
import com.romanticpipe.reviewcanvas.domain.Review;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "GetReviewDesignResponse", description = "리뷰 디자인 조회 응답")
public record GetMyReviewDesignResponse(Long id, Long reviewDesignId, Long shopAdminId) {

	public static GetMyReviewDesignResponse from(MyReviewDesign myReviewDesign) {
		return new GetMyReviewDesignResponse(myReviewDesign.getId(), myReviewDesign.getReviewDesignId(), myReviewDesign.getShopAdminId());
	}

}
