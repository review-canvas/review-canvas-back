package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewContainerResponse;

public interface ReviewContainerUseCase {
	GetReviewContainerResponse getReviewContainer(Integer shopAdminId);
}
