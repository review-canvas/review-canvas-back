package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.UpdateReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;

public interface ReviewUseCase {
	PageResponse<GetReviewResponse> getReviews(String productId, PageableRequest pageableRequest);

	UpdateReviewResponse updateReview(long reviewId, int score, String content);
}
