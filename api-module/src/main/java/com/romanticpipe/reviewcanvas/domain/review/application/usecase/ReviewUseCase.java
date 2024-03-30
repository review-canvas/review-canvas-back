package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.CreateReviewResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;

public interface ReviewUseCase {
	PageResponse<GetReviewResponse> getReviews(String productId, PageableRequest pageableRequest);

	CreateReviewResponse createReview(String productId, int score, String content);
}
