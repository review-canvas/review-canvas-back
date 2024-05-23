package com.romanticpipe.reviewcanvas;

import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.domain.ReviewStatus;
import com.romanticpipe.reviewcanvas.domain.User;
import org.springframework.test.util.ReflectionTestUtils;

public final class TestReviewFactory {

	public static Review createReview(Long productId, User user, String content, int score, String imageVideoUrls) {
		return Review.builder()
			.productId(productId)
			.user(user)
			.content(content)
			.score(score)
			.status(ReviewStatus.APPROVED)
			.imageVideoUrls(imageVideoUrls)
			.build();
	}

	public static Review createReview(Long reviewId, Long productId, User user, String content, int score,
									  String imageVideoUrls) {
		Review review = Review.builder()
			.productId(productId)
			.user(user)
			.content(content)
			.score(score)
			.status(ReviewStatus.APPROVED)
			.imageVideoUrls(imageVideoUrls)
			.build();
		ReflectionTestUtils.setField(review, "id", reviewId);
		return review;
	}
}
