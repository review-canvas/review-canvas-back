package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

public interface ReviewLikeUseCase {

	void deleteReviewLikeForUser(String mallId, String memberId, long reviewId);

	void deleteReviewLikeForShopAdmin(Integer adminId, Long reviewId);
}
