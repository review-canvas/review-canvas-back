package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import org.springframework.stereotype.Component;

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
	public void deleteReviewLike(String mallId, String memberId, long reviewId) {
		User user = userService.validByMemberIdAndMallId(memberId, mallId);
		Review review = reviewService.validById(reviewId);
		reviewLikeService.deleteReviewLike(user.getId(), review.getId(), null);
	}
}
