package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewLikeRequest;

public interface ReviewLikeUseCase {

	int getReviewLikeCount(Long reviewId);

	void createReviewLikeForUser(Long reviewId, CreateReviewLikeRequest createReviewLikeRequest);

	void createReviewLikeForShopAdmin(Integer shopAdminId, Long reviewId);

	void deleteReviewLikeForUser(String mallId, String memberId, long reviewId);

	void deleteReviewLikeForShopAdmin(Integer adminId, Long reviewId);

}
