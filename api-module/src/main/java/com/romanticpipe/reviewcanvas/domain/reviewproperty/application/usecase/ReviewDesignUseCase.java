package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateDesignViewRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewDesignViewResponse;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewDesignWriteResponse;

public interface ReviewDesignUseCase {
	GetReviewDesignViewResponse getReviewDesignView(Integer shopAdminId);

	void updateReviewDesignView(Integer shopAdminId, UpdateDesignViewRequest updateDesignViewRequest);

	void resetReviewDesignView(Integer shopAdminId);

	GetReviewDesignWriteResponse getReviewDesignWrite(Integer shopAdminId);
}
