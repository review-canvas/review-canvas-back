package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.reponse.GetReviewContainerResponse;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateContainerRequest;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewContainer;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewContainerService;

import lombok.RequiredArgsConstructor;

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
			updateContainerRequest.paddingLeft(),
			updateContainerRequest.paddingRight(),
			updateContainerRequest.paddingTop(),
			updateContainerRequest.paddingBottom(),
			updateContainerRequest.background(),
			updateContainerRequest.boarderLeft(),
			updateContainerRequest.boarderRight(),
			updateContainerRequest.boarderTop(),
			updateContainerRequest.boarderBottom(),
			updateContainerRequest.boarderColor(),
			updateContainerRequest.shadow()
		);
	}
}
