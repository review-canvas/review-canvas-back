package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import org.springframework.stereotype.Component;

import com.romanticpipe.reviewcanvas.common.util.TransactionUtils;
import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewLikeRequest;
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
	private final UserUseCase userUseCase;
	private final TransactionUtils transactionUtils;

	@Override
	public void createReviewLikeForUser(Long reviewId, CreateReviewLikeRequest createReviewLikeRequest) {
		User user = transactionUtils.executeInReadTransaction(status -> {
			reviewService.validById(reviewId);
			return userService.findUser(createReviewLikeRequest.memberId(), createReviewLikeRequest.mallId());
		}).orElseGet(() ->
			userUseCase.createSaveUser(createReviewLikeRequest.mallId(), createReviewLikeRequest.memberId())
		);

		transactionUtils.executeWithoutResultInWriteTransaction(
			status -> {
				reviewLikeService.validateIsLike(reviewId, user.getId());
				reviewLikeService.createAndSave(reviewId, user.getId(), null);
			}
		);
	}
}
