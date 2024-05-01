package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateLayoutRequest;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewColumnService;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewLayoutService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@RequiredArgsConstructor
public class ReviewLayoutUseCaseImpl implements ReviewLayoutUseCase {

	private final ReviewLayoutService reviewLayoutService;
	private final ReviewColumnService reviewColumnService;

	@Override
	@Transactional
	public void updateLayout(Integer shopAdminId, UpdateLayoutRequest updateLayoutRequest) {
		ReviewLayout reviewLayout = reviewLayoutService.validateById(shopAdminId);
		reviewLayout.update(
			updateLayoutRequest.bestReviewAreaActivation(),
			updateLayoutRequest.reviewStaticsAreaActivation(),
			updateLayoutRequest.imageReviewAreaActivation(),
			updateLayoutRequest.focusAreaLayout(),
			updateLayoutRequest.imageReviewAreaLayout(),
			updateLayoutRequest.reviewLayoutDesign());
	}

	@Override
	public ReviewColumn getColumn(Integer shopAdminId) {
		reviewColumnService.validateById(shopAdminId);
		return reviewColumnService.findById(shopAdminId).get();
	}
}
