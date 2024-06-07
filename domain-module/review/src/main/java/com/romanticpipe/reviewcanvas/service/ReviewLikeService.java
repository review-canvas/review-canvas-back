package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.domain.ReviewLike;
import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ReviewErrorCode;
import com.romanticpipe.reviewcanvas.repository.ReviewLikeRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewLikeService {

	private final ReviewLikeRepository reviewLikeRepository;
	private final EntityManager entityManager;

	public void save(ReviewLike reviewLike) {
		reviewLikeRepository.save(reviewLike);
	}

	public int getReviewLikeCount(Long reviewId) {
		return reviewLikeRepository.countAllByReviewId(reviewId);
	}

	public void validateIsLike(Long reviewId, Long userId, Integer shopAdminId) {
		reviewLikeRepository.findByReviewIdAndUserIdAndShopAdminId(reviewId, userId, shopAdminId)
			.ifPresent(reviewLike -> {
				throw new BusinessException(ReviewErrorCode.ALREADY_LIKED_REVIEW);
			});
	}

	public void createAndSave(Long reviewId, Long userId, Integer shopAdminId) {
		ReviewLike reviewLike = ReviewLike.builder()
			.review(entityManager.getReference(Review.class, reviewId))
			.user(entityManager.getReference(User.class, userId))
			.shopAdminId(shopAdminId)
			.build();
		reviewLikeRepository.save(reviewLike);
	}

	public void deleteReviewLike(Long reviewId, Long userId, Integer shopAdminId) {
		reviewLikeRepository.delete(
			reviewLikeRepository.findByReviewIdAndUserIdAndShopAdminId(reviewId, userId, shopAdminId)
				.orElseThrow(() -> new BusinessException(ReviewErrorCode.ALREADY_UNLIKED_REVIEW))
		);
	}

	public boolean isUserLikeThisReview(Long reviewId, Long userId) {
		return reviewLikeRepository.existsByReviewIdAndUserId(reviewId, userId);
	}

	public boolean isShopAdminLikeThisReview(Long reviewId, Integer shopAdminId) {
		return reviewLikeRepository.existsByReviewIdAndShopAdminId(reviewId, shopAdminId);
	}
}
