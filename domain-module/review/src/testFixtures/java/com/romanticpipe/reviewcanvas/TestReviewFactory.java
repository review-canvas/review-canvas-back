package com.romanticpipe.reviewcanvas;

import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.domain.ReviewStatus;
import org.springframework.test.util.ReflectionTestUtils;

public final class TestReviewFactory {

	public static Review createReview(Long productId, Long userId, String content, int score) {
		return new Review(productId, userId, content, score, ReviewStatus.APPROVED);
	}

	public static Review createReview(Long reviewId, Long productId, Long userId, String content, int score) {
		Review review = new Review(productId, userId, content, score, ReviewStatus.APPROVED);
		ReflectionTestUtils.setField(review, "id", reviewId);
		return review;
	}
}
