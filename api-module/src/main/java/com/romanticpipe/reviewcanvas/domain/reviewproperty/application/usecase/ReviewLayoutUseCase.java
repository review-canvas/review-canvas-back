package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateLayoutRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewLayoutResponse;

public interface ReviewLayoutUseCase {
	void updateLayout(Integer shopAdminId, UpdateLayoutRequest updateLayoutRequest);

	GetReviewLayoutResponse getReviewLayout(Integer shopAdminId);

	void initializeReviewLayout(Integer shopAdminId);
}
