package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;

public interface ReviewUseCase {

	PageResponse<GetReviewResponse> getReviewsByProductId(String productId, PageableRequest pageableRequest);

	PageResponse<GetReviewResponse> getReviewsByUserId(String userId, PageableRequest pageableRequest);

	void createReview(String productId, CreateReviewRequest createReviewRequest);

	void updateReview(long reviewId, UpdateReviewRequest updateReviewRequest);


}
