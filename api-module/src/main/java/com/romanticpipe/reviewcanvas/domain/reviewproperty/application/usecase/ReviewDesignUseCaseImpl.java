package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateDesignViewRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewDesignViewResponse;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewDesignView;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewDesignViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
class ReviewDesignUseCaseImpl implements ReviewDesignUseCase {

	private final ReviewDesignViewService reviewDesignViewService;

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
}
