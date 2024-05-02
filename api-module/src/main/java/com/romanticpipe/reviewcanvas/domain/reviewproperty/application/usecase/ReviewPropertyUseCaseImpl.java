package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import org.springframework.stereotype.Component;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewPropertyResponse;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewContainer;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewTitle;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewContainerService;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewLayoutService;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewTitleService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewPropertyUseCaseImpl implements ReviewPropertyUseCase {

	private final ReviewContainerService reviewContainerService;
	private final ReviewLayoutService reviewLayoutService;
	private final ReviewTitleService reviewTitleService;

	@Override
	public GetReviewPropertyResponse getAllReviewProperty(Integer shopAdminId) {
		ReviewContainer reviewContainer = reviewContainerService.validateByShopAdminId(shopAdminId);
		ReviewLayout reviewLayout = reviewLayoutService.validateById(shopAdminId);
		ReviewTitle reviewTitle = reviewTitleService.validTitleByShopAdminId(shopAdminId);
		return GetReviewPropertyResponse.from(reviewLayout, reviewContainer, reviewTitle);
	}
}
