package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.service.ReviewLikeService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewLikeUseCaseImpl implements ReviewLikeUseCase {

	private final ReviewLikeService reviewLikeService;

	@Override
	@Transactional(readOnly = true)
	public int getReviewLikeCount(Long reviewId) {
		return reviewLikeService.getReviewLikeCount(reviewId);
	}
}
