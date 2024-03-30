package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.CreateReviewResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.service.ProductReader;
import com.romanticpipe.reviewcanvas.service.ReviewReader;
import com.romanticpipe.reviewcanvas.service.ReviewWriter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ReviewUseCaseImpl implements ReviewUseCase {

	private final ProductReader productReader;
	private final ReviewReader reviewReader;
	private final ReviewWriter reviewWriter;

	@Override
	@Transactional(readOnly = true)
	public PageResponse<GetReviewResponse> getReviews(String productId, PageableRequest pageableRequest) {
		return reviewReader.findByProductId(productId, pageableRequest)
			.map(GetReviewResponse::from);
	}

	@Override
	@Transactional
	public CreateReviewResponse createReview(String productId, int score, String content) {
		Product product = productReader.findByProductId(productId);
		if (product == null) {
			return null;
		}
		reviewWriter.save(productId, score, content);
		return new CreateReviewResponse(productId, score, content);
	}

}
