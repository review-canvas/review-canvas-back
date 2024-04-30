package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.reponse.GetReviewContainerResponse;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateContainerRequest;

public interface ReviewContainerUseCase {
	GetReviewContainerResponse getReviewContainer(Integer shopAdminId);

	void updateReviewContainer(Integer shopAdminId, UpdateContainerRequest updateContainerRequest);
}
