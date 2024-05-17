package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewForUserResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilter;

public interface ReviewUseCase {
	PageResponse<GetReviewForUserResponse> getReviewsForUser(String mallId, Long productNo,
		PageableRequest pageableRequest, ReviewFilter filter);

	PageResponse<GetReviewResponse> getReviewsByUserId(String userId, PageableRequest pageableRequest);

	void createReview(Long productId, CreateReviewRequest createReviewRequest);

	void updateReview(long reviewId, UpdateReviewRequest updateReviewRequest);

	GetReviewForUserResponse getReviewForUser(Long reviewId);
}
