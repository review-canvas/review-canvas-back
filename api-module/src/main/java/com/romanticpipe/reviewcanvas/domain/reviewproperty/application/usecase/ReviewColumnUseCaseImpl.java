package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateColumnRequest;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewColumnService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewColumnUseCaseImpl implements ReviewColumnUseCase {

	private final ReviewColumnService reviewColumnService;

	@Override
	@Transactional(readOnly = true)
	public ReviewColumn getColumnByShopAdminId(Integer shopAdminId) {
		ReviewColumn reviewColumn = reviewColumnService.validateByShopAdminId(shopAdminId);
		return reviewColumn;
	}

	@Override
	@Transactional
	public void updateReviewColumn(Integer shopAdminId, UpdateColumnRequest updateColumnRequest) {
		ReviewColumn reviewColumn = reviewColumnService.validateByShopAdminId(shopAdminId);
		reviewColumn.update(updateColumnRequest.width(),
			updateColumnRequest.padding(),
			updateColumnRequest.margin(),
			updateColumnRequest.background(),
			updateColumnRequest.border(),
			updateColumnRequest.borderColor(),
			updateColumnRequest.shadow());
	}

	@Override
	@Transactional
	public void resetReviewColumn(Integer shopAdminId) {
		ReviewColumn reviewColumn = reviewColumnService.validateByShopAdminId(shopAdminId);
		reviewColumn.reset();
	}
}
