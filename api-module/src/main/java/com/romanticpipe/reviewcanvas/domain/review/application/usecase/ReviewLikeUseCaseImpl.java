package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import org.springframework.stereotype.Component;

import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewLikeRequest;
import com.romanticpipe.reviewcanvas.service.ReviewLikeService;
import com.romanticpipe.reviewcanvas.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewLikeUseCaseImpl implements ReviewLikeUseCase {

	private final ReviewLikeService reviewLikeService;
	private final ReviewService reviewService;

	@Override
	public void createReviewLike(Long reviewId, CreateReviewLikeRequest createReviewLikeRequest) {

	}
}
