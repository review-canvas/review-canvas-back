package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import org.springframework.stereotype.Component;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateReviewTitleAttributeRequest;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewTitle;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewTitleService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewTitleUseCaseImpl implements ReviewTitleUseCase {

	private final ReviewTitleService reviewTitleService;

	@Override
	public void updateReviewTitleAttribute(Integer shopAdminId,
		UpdateReviewTitleAttributeRequest updateReviewTitleAttributeRequest) {

		ReviewTitle reviewTitle = reviewTitleService.validTitleByShopAdminId(shopAdminId);
		reviewTitle.update(
			updateReviewTitleAttributeRequest.getTitle(),
			updateReviewTitleAttributeRequest.getTitleAlignmentPosition(),
			updateReviewTitleAttributeRequest.getTitlePadding(),
			updateReviewTitleAttributeRequest.getTitleFont(),
			updateReviewTitleAttributeRequest.getTitleBoarder(),
			updateReviewTitleAttributeRequest.getDescriptionBackGround()
		);

		ReviewTitle reviewDescription = reviewTitleService.validDescriptionByShopAdminId(shopAdminId);
		reviewDescription.update(
			updateReviewTitleAttributeRequest.getDescription(),
			updateReviewTitleAttributeRequest.getDescriptionAlignmentPosition(),
			updateReviewTitleAttributeRequest.getDescriptionPadding(),
			updateReviewTitleAttributeRequest.getDescriptionFont(),
			updateReviewTitleAttributeRequest.getDescriptionBoarder(),
			updateReviewTitleAttributeRequest.getDescriptionBackGround()
		);
	}

	@Override
	public void resetReviewTitleAttribute(Integer shopAdminId) {
		ReviewTitle reviewTitle = reviewTitleService.validTitleByShopAdminId(shopAdminId);
		reviewTitle.reset();

		ReviewTitle reviewDescription = reviewTitleService.validDescriptionByShopAdminId(shopAdminId);
		reviewDescription.reset();
	}
}
