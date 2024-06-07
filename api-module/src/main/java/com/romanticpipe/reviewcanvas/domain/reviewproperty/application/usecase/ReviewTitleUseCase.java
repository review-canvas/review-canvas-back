package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateReviewTitleRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewTitleResponse;

public interface ReviewTitleUseCase {

	void updateReviewTitle(Integer shopAdminId,
						   UpdateReviewTitleRequest updateReviewTitleRequest);

	void initializeReviewTitle(Integer shopAdminId);

	GetReviewTitleResponse getReviewTitle(Integer shopAdminId);
}
