package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateLayoutRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewLayoutResponse;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewLayoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
			updateLayoutRequest.reviewStatisticsAreaActivation(),
			updateLayoutRequest.imageReviewAreaActivation(),
			updateLayoutRequest.focusAreaLayout(),
			updateLayoutRequest.imageReviewAreaLayout(),
			updateLayoutRequest.reviewLayoutDesign());
	}

	@Override
	@Transactional(readOnly = true)
	public GetReviewLayoutResponse getReviewLayout(Integer shopAdminId) {
		return GetReviewLayoutResponse.from(reviewLayoutService.validateByShopAdminId(shopAdminId));
	}

	@Override
	@Transactional
	public void initializeReviewLayout(Integer shopAdminId) {
		ReviewLayout reviewLayout = reviewLayoutService.validateByShopAdminId(shopAdminId);
		reviewLayout.initialize();
	}
}
