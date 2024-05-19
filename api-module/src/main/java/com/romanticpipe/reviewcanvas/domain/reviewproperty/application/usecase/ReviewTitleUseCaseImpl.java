package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateReviewTitleRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewTitleResponse;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewTitle;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewTitleService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewTitleUseCaseImpl implements ReviewTitleUseCase {

	private final ReviewTitleService reviewTitleService;

	@Override
	@Transactional
	public void updateReviewTitle(Integer shopAdminId,
		UpdateReviewTitleRequest updateReviewTitleRequest) {

		ReviewTitle reviewTitle = reviewTitleService.validateTitleByShopAdminId(shopAdminId);
		reviewTitle.update(
			updateReviewTitleRequest.title(),
			updateReviewTitleRequest.titleAlignmentPosition(),
			updateReviewTitleRequest.titlePadding().toVO(),
			updateReviewTitleRequest.titleFont().toVO(),
			updateReviewTitleRequest.titleBorder().toVO(),
			updateReviewTitleRequest.titleBorderColor(),
			updateReviewTitleRequest.titleBackGround()
		);

		ReviewTitle reviewDescription = reviewTitleService.validateDescriptionByShopAdminId(shopAdminId);
		reviewDescription.update(
			updateReviewTitleRequest.description(),
			updateReviewTitleRequest.descriptionAlignmentPosition(),
			updateReviewTitleRequest.descriptionPadding().toVO(),
			updateReviewTitleRequest.descriptionFont().toVO(),
			updateReviewTitleRequest.descriptionBorder().toVO(),
			updateReviewTitleRequest.descriptionBorderColor(),
			updateReviewTitleRequest.descriptionBackGround()
		);
	}

	@Override
	@Transactional
	public void initializeReviewTitle(Integer shopAdminId) {
		ReviewTitle reviewTitle = reviewTitleService.validateTitleByShopAdminId(shopAdminId);
		reviewTitle.initializeTitle();

		ReviewTitle reviewDescription = reviewTitleService.validateDescriptionByShopAdminId(shopAdminId);
		reviewDescription.initializeDescription();
	}

	@Override
	@Transactional(readOnly = true)
	public GetReviewTitleResponse getReviewTitle(Integer shopAdminId) {
		ReviewTitle reviewTitle = reviewTitleService.validateTitleByShopAdminId(shopAdminId);
		ReviewTitle reviewDescription = reviewTitleService.validateDescriptionByShopAdminId(shopAdminId);
		return GetReviewTitleResponse.from(reviewTitle, reviewDescription);
	}

}
