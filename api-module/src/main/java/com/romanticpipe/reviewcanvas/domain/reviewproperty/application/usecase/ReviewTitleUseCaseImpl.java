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

		ReviewTitle reviewTitle = reviewTitleService.validTitleByShopAdminId(shopAdminId);
		reviewTitle.update(
			updateReviewTitleRequest.title(),
			updateReviewTitleRequest.titleAlignmentPosition(),
			updateReviewTitleRequest.titlePadding(),
			updateReviewTitleRequest.titleFont(),
			updateReviewTitleRequest.titleBoarder(),
			updateReviewTitleRequest.titleBoarderColor(),
			updateReviewTitleRequest.titleBackGround()
		);

		ReviewTitle reviewDescription = reviewTitleService.validDescriptionByShopAdminId(shopAdminId);
		reviewDescription.update(
			updateReviewTitleRequest.description(),
			updateReviewTitleRequest.descriptionAlignmentPosition(),
			updateReviewTitleRequest.descriptionPadding(),
			updateReviewTitleRequest.descriptionFont(),
			updateReviewTitleRequest.descriptionBoarder(),
			updateReviewTitleRequest.descriptionBoarderColor(),
			updateReviewTitleRequest.descriptionBackGround()
		);
	}

	@Override
	@Transactional
	public void resetReviewTitle(Integer shopAdminId) {
		ReviewTitle reviewTitle = reviewTitleService.validTitleByShopAdminId(shopAdminId);
		reviewTitle.resetTitle();

		ReviewTitle reviewDescription = reviewTitleService.validDescriptionByShopAdminId(shopAdminId);
		reviewDescription.resetDescription();
	}

	@Override
	@Transactional(readOnly = true)
	public GetReviewTitleResponse getReviewTitle(Integer shopAdminId) {
		ReviewTitle reviewTitle = reviewTitleService.validTitleByShopAdminId(shopAdminId);
		ReviewTitle reviewDescription = reviewTitleService.validDescriptionByShopAdminId(shopAdminId);
		return GetReviewTitleResponse.from(reviewTitle, reviewDescription);
	}
}
