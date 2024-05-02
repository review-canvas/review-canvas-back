package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateColumnRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateLayoutRequest;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewColumnService;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewLayoutService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewColumnUseCaseImpl implements ReviewColumnUseCase {

	private final ReviewColumnService reviewColumnService;

	@Override
	public ReviewColumn getColumnByShopAdminId(Integer shopAdminId) {
		ReviewColumn reviewColumn = reviewColumnService.validateById(shopAdminId);
		return reviewColumn;
	}

	@Override
	@Transactional
	public void updateReviewColumn(Integer shopAdminId, UpdateColumnRequest updateColumnRequest) {
		ReviewColumn reviewColumn = reviewColumnService.validateById(shopAdminId);
		reviewColumn.update(updateColumnRequest.width(),
			updateColumnRequest.padding(),
			updateColumnRequest.margin(),
			updateColumnRequest.background(),
			updateColumnRequest.boarder(),
			updateColumnRequest.boarderColor(),
			updateColumnRequest.shadow());
		// reviewColumnService.update(reviewColumn);
	}

	@Override
	@Transactional
	public void resetReviewColumn(Integer shopAdminId) {
		ReviewColumn reviewColumn = reviewColumnService.validateById(shopAdminId);
		reviewColumn.reset();
	}
}
