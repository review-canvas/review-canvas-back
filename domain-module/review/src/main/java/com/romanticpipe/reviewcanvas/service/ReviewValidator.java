package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ReviewErrorCode;
import com.romanticpipe.reviewcanvas.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewValidator {

	private final ReviewRepository reviewRepository;

	public Review validById(long reviewId) {
		System.out.println("!!reviewId : " + reviewId);
		System.out.println("!!reviewId : " + reviewRepository.findAll().size());

		Optional<Review> reviewOptional = Optional.ofNullable(reviewRepository.findById(reviewId));
		if (reviewOptional.isEmpty()) {
			throw new BusinessException(ReviewErrorCode.REVIEW_NOT_FOUND);
		}
		return reviewOptional.get();
	}

}
