package com.romanticpipe.reviewcanvas;

import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.domain.ReviewStatus;
import org.springframework.test.util.ReflectionTestUtils;

public final class TestReviewFactory {

	public static Review createReview(Long productId, Long userId, String content, int score, String imageVideoUrls) {
		return Review.builder()
			.productId(productId)
			.userId(userId)
			.content(content)
			.score(score)
			.status(ReviewStatus.APPROVED)
			.imageVideoUrls(imageVideoUrls)
			.build();
	}

	public static Review createReview(Long reviewId, Long productId, Long userId, String content, int score,
									  String imageVideoUrls) {
		Review review = Review.builder()
			.productId(productId)
			.userId(userId)
			.content(content)
			.score(score)
			.status(ReviewStatus.APPROVED)
			.imageVideoUrls(imageVideoUrls)
			.build();
		ReflectionTestUtils.setField(review, "id", reviewId);
		return review;
	}
}
