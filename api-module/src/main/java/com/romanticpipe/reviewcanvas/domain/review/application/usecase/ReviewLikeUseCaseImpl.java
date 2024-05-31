package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.admin.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.admin.service.ShopAdminService;
import com.romanticpipe.reviewcanvas.config.TransactionUtils;
import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.domain.ReviewLike;
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
	private final ShopAdminService shopAdminService;
	private final UserUseCase userUseCase;
	private final TransactionUtils transactionUtils;

	@Override
	@Transactional(readOnly = true)
	public int getReviewLikeCount(Long reviewId) {
		reviewService.validById(reviewId);
		return reviewLikeService.getReviewLikeCount(reviewId);
	}

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
				reviewLikeService.validateIsLike(reviewId, user.getId(), null);
				reviewLikeService.createAndSave(reviewId, user.getId(), null);
			}
		);
	}

	@Override
	@Transactional
	public void createReviewLikeForShopAdmin(Integer shopAdminId, Long reviewId) {
		ShopAdmin shopAdmin = shopAdminService.validateById(shopAdminId);
		Review review = reviewService.validById(reviewId);
		reviewLikeService.validateIsLike(reviewId, null, shopAdminId);
		ReviewLike reviewLike = ReviewLike.builder()
			.review(review)
			.shopAdminId(shopAdmin.getId())
			.build();
		reviewLikeService.save(reviewLike);
	}

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
