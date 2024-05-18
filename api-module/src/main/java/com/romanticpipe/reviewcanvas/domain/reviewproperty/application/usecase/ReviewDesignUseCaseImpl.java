package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateDesignViewRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateDesignWriteRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewDesignViewResponse;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewDesignWriteResponse;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewDesignView;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewDesignWrite;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewDesignViewService;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewDesignWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
class ReviewDesignUseCaseImpl implements ReviewDesignUseCase {

	private final ReviewDesignViewService reviewDesignViewService;
	private final ReviewDesignWriteService reviewDesignWriteService;

	@Override
	@Transactional(readOnly = true)
	public GetReviewDesignViewResponse getReviewDesignView(Integer shopAdminId) {
		return GetReviewDesignViewResponse.from(reviewDesignViewService.validateByShopAdminId(shopAdminId));
	}

	@Override
	@Transactional
	public void updateReviewDesignView(Integer shopAdminId, UpdateDesignViewRequest updateDesignViewRequest) {
		ReviewDesignView reviewDesignView = reviewDesignViewService.validateByShopAdminId(shopAdminId);
		reviewDesignView.update(updateDesignViewRequest.toDto());
	}

	@Override
	@Transactional
	public void resetReviewDesignView(Integer shopAdminId) {
		ReviewDesignView reviewDesignView = reviewDesignViewService.validateByShopAdminId(shopAdminId);
		reviewDesignView.reset();
	}

	@Override
	@Transactional(readOnly = true)
	public GetReviewDesignWriteResponse getReviewDesignWrite(Integer shopAdminId) {
		return GetReviewDesignWriteResponse.from(reviewDesignWriteService.validateByShopAdminId(shopAdminId));
	}

	@Override
	@Transactional
	public void updateReviewDesignWrite(Integer shopAdminId, UpdateDesignWriteRequest updateDesignWriteRequest) {
		ReviewDesignWrite reviewDesignWrite = reviewDesignWriteService.validateByShopAdminId(shopAdminId);
		reviewDesignWrite.update(updateDesignWriteRequest.toDto());
	}
}
