package com.romanticpipe.reviewcanvas.domain.reviewdesign.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewdesign.application.usecase.response.GetReviewDesignResponse;

public interface ReviewDesignUseCase {

	GetReviewDesignResponse getSelectedReviewDesign(long shopAdminId, String position);

}
