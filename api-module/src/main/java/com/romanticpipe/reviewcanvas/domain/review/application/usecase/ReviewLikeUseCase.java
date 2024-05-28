package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewLikeRequest;

public interface ReviewLikeUseCase {

	void createReviewLike(Long reviewId, CreateReviewLikeRequest createReviewLikeRequest);
}
