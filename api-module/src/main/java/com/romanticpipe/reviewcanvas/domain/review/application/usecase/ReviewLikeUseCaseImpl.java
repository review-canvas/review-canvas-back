package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.service.ReviewLikeService;
import com.romanticpipe.reviewcanvas.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewLikeUseCaseImpl implements ReviewLikeUseCase {

	private final ReviewLikeService reviewLikeService;
	private final ReviewService reviewService;

	@Override
	@Transactional(readOnly = true)
	public int getReviewLikeCount(Long reviewId) {
		reviewService.validById(reviewId);
		return reviewLikeService.getReviewLikeCount(reviewId);
	}
}
