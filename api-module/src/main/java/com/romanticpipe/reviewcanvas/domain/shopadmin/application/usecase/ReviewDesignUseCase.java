package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.UpdateReviewDesignRequest;

public interface ReviewDesignUseCase {
	void updateReviewDesign(Integer adminId, Integer reviewDesignId,
		UpdateReviewDesignRequest updateReviewDesignRequest);
}
