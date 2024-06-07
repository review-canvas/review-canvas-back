package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateContainerRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewContainerResponse;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewContainer;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewContainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
class ReviewContainerUseCaseImpl implements ReviewContainerUseCase {

	private final ReviewContainerService reviewContainerService;

	@Override
	@Transactional(readOnly = true)
	public GetReviewContainerResponse getReviewContainer(Integer shopAdminId) {
		return GetReviewContainerResponse.from(reviewContainerService.validateByShopAdminId(shopAdminId));
	}

	@Override
	@Transactional
	public void updateReviewContainer(Integer shopAdminId, UpdateContainerRequest updateContainerRequest) {
		ReviewContainer reviewContainer = reviewContainerService.validateByShopAdminId(shopAdminId);
		reviewContainer.update(
			updateContainerRequest.width(),
			updateContainerRequest.padding().toVO(),
			updateContainerRequest.background(),
			updateContainerRequest.border().toVO(),
			updateContainerRequest.borderColor(),
			updateContainerRequest.shadow()
		);
	}

	@Override
	@Transactional
	public void resetReviewContainer(Integer shopAdminId) {
		ReviewContainer reviewContainer = reviewContainerService.validateByShopAdminId(shopAdminId);
		reviewContainer.reset();
	}
}
