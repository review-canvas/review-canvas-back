package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateDesignViewRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewDesignViewResponse;

public interface ReviewDesignUseCase {
	GetReviewDesignViewResponse getReviewDesignView(Integer shopAdminId);

	void updateReviewDesignView(Integer shopAdminId, UpdateDesignViewRequest updateDesignViewRequest);
}
