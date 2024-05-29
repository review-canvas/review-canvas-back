package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

public interface ReviewLikeUseCase {

	void deleteReviewLike(String mallId, String memberId, long reviewId);
}
