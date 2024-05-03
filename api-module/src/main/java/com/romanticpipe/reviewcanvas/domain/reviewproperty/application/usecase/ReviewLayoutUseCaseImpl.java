package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateLayoutRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.ReviewLayoutResponse;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewLayoutService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewLayoutUseCaseImpl implements ReviewLayoutUseCase {

	private final ReviewLayoutService reviewLayoutService;

	@Override
	@Transactional
	public void updateLayout(Integer shopAdminId, UpdateLayoutRequest updateLayoutRequest) {
		ReviewLayout reviewLayout = reviewLayoutService.validateByShopAdminId(shopAdminId);
		reviewLayout.update(
			updateLayoutRequest.bestReviewAreaActivation(),
			updateLayoutRequest.reviewStaticsAreaActivation(),
			updateLayoutRequest.imageReviewAreaActivation(),
			updateLayoutRequest.focusAreaLayout(),
			updateLayoutRequest.imageReviewAreaLayout(),
			updateLayoutRequest.reviewLayoutDesign());
	}

	@Override
	@Transactional(readOnly = true)
	public ReviewLayoutResponse getReviewLayout(Integer shopAdminId) {
		return ReviewLayoutResponse.from(reviewLayoutService.validateByShopAdminId(shopAdminId));
	}

	@Override
	@Transactional
	public void initializeReviewLayout(Integer shopAdminId) {
		ReviewLayout reviewLayout = reviewLayoutService.validateByShopAdminId(shopAdminId);
		reviewLayout.initialize();
	}
}
