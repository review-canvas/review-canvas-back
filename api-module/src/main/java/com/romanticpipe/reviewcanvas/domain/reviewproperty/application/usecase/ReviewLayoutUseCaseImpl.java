package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.LayoutRequest;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewLayoutService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewLayoutUseCaseImpl implements ReviewLayoutUseCase {

	private final ReviewLayoutService reviewLayoutService;

	@Override
	@Transactional
	public void updateLayout(Integer adminId, LayoutRequest layoutRequest) {
		ReviewLayout reviewLayout = reviewLayoutService.validById(adminId);
		reviewLayout.update(
			layoutRequest.bestReviewAreaActivation(),
			layoutRequest.reviewStaticsAreaActivation(),
			layoutRequest.imageReviewAreaActivation(),
			layoutRequest.focusAreaLayout(),
			layoutRequest.imageReviewAreaLayout(),
			layoutRequest.reviewLayoutDesign());
	}
}
