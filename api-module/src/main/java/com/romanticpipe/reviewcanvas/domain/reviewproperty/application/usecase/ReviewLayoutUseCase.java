package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateLayoutRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.ReviewLayoutResponse;

public interface ReviewLayoutUseCase {
	void updateLayout(Integer adminId, UpdateLayoutRequest updateLayoutRequest);

	ReviewLayoutResponse getReviewLayout();
}
