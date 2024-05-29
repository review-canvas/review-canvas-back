package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.admin.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.admin.service.ShopAdminService;
import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.service.ReviewLikeService;
import com.romanticpipe.reviewcanvas.service.ReviewService;
import com.romanticpipe.reviewcanvas.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewLikeUseCaseImpl implements ReviewLikeUseCase {

	private final ReviewLikeService reviewLikeService;
	private final ReviewService reviewService;
	private final UserService userService;
	private final ShopAdminService shopAdminService;

	@Override
	@Transactional
	public void deleteReviewLikeForUser(String mallId, String memberId, long reviewId) {
		User user = userService.validByMemberIdAndMallId(memberId, mallId);
		Review review = reviewService.validById(reviewId);
		reviewLikeService.deleteReviewLike(review.getId(), user.getId(), null);
	}

	@Override
	@Transactional
	public void deleteReviewLikeForShopAdmin(Integer shopAdminId, Long reviewId) {
		ShopAdmin shopAdmin = shopAdminService.validateById(shopAdminId);
		Review review = reviewService.validById(reviewId);
		reviewLikeService.deleteReviewLike(review.getId(), null, shopAdmin.getId());
	}
}
