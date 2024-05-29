package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	@Transactional
	public void deleteReviewLikeForUser(String mallId, String memberId, long reviewId) {
		User user = userService.validByMemberIdAndMallId(memberId, mallId);
		Review review = reviewService.validById(reviewId);
		reviewLikeService.deleteReviewLike(user.getId(), review.getId(), null);
	}

	@Override
	public void deleteReviewLikeForShopAdmin(Integer adminId, Long reviewId) {

	}
}
