package com.romanticpipe.reviewcanvas.domain.reviewdesign.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignPosition;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.domain.reviewdesign.application.usecase.response.GetReviewDesignResponse;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ReviewDesignErrorCode;
import com.romanticpipe.reviewcanvas.service.ReviewDesignValidator;
import com.romanticpipe.reviewcanvas.service.ShopAdminValidator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewDesignUseCaseImpl implements ReviewDesignUseCase {

	private final ShopAdminValidator shopAdminValidator;
	private final ReviewDesignValidator reviewDesignValidator;

	@Override
	@Transactional
	public GetReviewDesignResponse getSelectedReviewDesign(long shopAdminId, String position) {
		ShopAdmin shopAdmin = shopAdminValidator.validById(shopAdminId);
		ReviewDesign reviewDesign;

		if (ReviewDesignPosition.valueOf(position) == ReviewDesignPosition.MODAL) {
			reviewDesign = reviewDesignValidator.validById(shopAdmin.getModalReviewDesignId());
		} else if (ReviewDesignPosition.valueOf(position) == ReviewDesignPosition.LIST) {
			reviewDesign = reviewDesignValidator.validById(shopAdmin.getListReviewDesignId());
		} else {
			throw new BusinessException(ReviewDesignErrorCode.REVIEW_DESIGN_INVALID_POSITION);
		}

		return new GetReviewDesignResponse(
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
