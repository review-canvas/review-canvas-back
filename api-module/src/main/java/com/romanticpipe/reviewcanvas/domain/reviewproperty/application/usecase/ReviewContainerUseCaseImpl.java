package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewContainerResponse;
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
}
