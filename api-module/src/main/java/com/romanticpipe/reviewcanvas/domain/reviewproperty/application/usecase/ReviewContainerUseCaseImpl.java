package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.reponse.GetReviewContainerResponse;
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
}
