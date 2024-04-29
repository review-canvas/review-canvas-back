package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.UpdateReviewDesignRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetApplicableReviewDesignResponse;

import java.util.List;

public interface ReviewDesignUseCase {
	void updateReviewDesign(Integer adminId, Integer reviewDesignId,
							UpdateReviewDesignRequest updateReviewDesignRequest);

	List<GetApplicableReviewDesignResponse> getApplicableReviewDesign(Integer shopAdminId);
}
