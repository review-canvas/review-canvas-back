package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;

public interface ReviewUseCase {
	PageResponse<GetReviewResponse> getReviewsByProductId(String productId, PageableRequest pageableRequest);

	PageResponse<GetReviewResponse> getReviewsByUserId(String userId, PageableRequest pageableRequest);

}
