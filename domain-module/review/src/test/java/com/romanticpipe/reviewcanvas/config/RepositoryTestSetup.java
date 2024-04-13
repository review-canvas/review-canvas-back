package com.romanticpipe.reviewcanvas.config;

import com.romanticpipe.reviewcanvas.TestReviewFactory;
import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.repository.ReviewRepository;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Transactional
public abstract class RepositoryTestSetup {

	protected ReviewRepository reviewRepository;

	protected Review createReviewEntity(String productId, String userId) {
		Review review = TestReviewFactory.createReview(productId, userId, "content", 5);
		return reviewRepository.save(review);
	}
}
