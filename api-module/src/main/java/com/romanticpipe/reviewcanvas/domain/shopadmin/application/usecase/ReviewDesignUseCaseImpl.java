package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.UpdateReviewDesignRequest;
import com.romanticpipe.reviewcanvas.service.MyReviewDesignValidator;
import com.romanticpipe.reviewcanvas.service.ReviewDesignValidator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewDesignUseCaseImpl implements ReviewDesignUseCase {

	private final ReviewDesignValidator reviewDesignValidator;
	private final MyReviewDesignValidator myReviewDesignValidator;

	@Override
	@Transactional
	public void updateReviewDesign(Integer shopAdminId, Integer reviewDesignId,
		UpdateReviewDesignRequest updateReviewDesignRequest) {
		ReviewDesign reviewDesign = reviewDesignValidator.validById(reviewDesignId);
		myReviewDesignValidator.validateIsMyDesign(shopAdminId, reviewDesignId);

		reviewDesign.update(
			updateReviewDesignRequest.reviewDesignPosition(),
			updateReviewDesignRequest.themeName(),
			updateReviewDesignRequest.layoutType(),
			updateReviewDesignRequest.padding(),
			updateReviewDesignRequest.gap(),
			updateReviewDesignRequest.boxShadowColor(),
			updateReviewDesignRequest.boxShadowWidth(),
			updateReviewDesignRequest.borderColor(),
			updateReviewDesignRequest.borderTransparency(),
			updateReviewDesignRequest.borderWidth(),
			updateReviewDesignRequest.pagingType(),
			updateReviewDesignRequest.pagingNumber(),
			updateReviewDesignRequest.textAlign(),
			updateReviewDesignRequest.pointColor(),
			updateReviewDesignRequest.pointType(),
			updateReviewDesignRequest.lineEllipsis(),
			updateReviewDesignRequest.reviewDesignUrl());
	}
}
