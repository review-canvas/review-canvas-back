package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateReviewTitleAttributeRequest;

public interface ReviewTitleUseCase {

	void updateReviewTitleAttribute(Integer shopAdminId,
		UpdateReviewTitleAttributeRequest updateReviewTitleAttributeRequest);
}
