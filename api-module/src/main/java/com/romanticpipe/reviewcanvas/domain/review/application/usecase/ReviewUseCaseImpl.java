package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.domain.ReviewStatus;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.service.ProductValidator;
import com.romanticpipe.reviewcanvas.service.ReviewCreator;
import com.romanticpipe.reviewcanvas.service.ReviewReader;
import com.romanticpipe.reviewcanvas.service.ShopAdminReader;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ReviewUseCaseImpl implements ReviewUseCase {

	private final ShopAdminReader shopAdminReader;
	private final ProductValidator productValidator;
	private final ReviewReader reviewReader;
	private final ReviewCreator reviewCreator;

	@Override
	@Transactional(readOnly = true)
	public PageResponse<GetReviewResponse> getReviewsByProductId(String productId, PageableRequest pageableRequest) {
		return reviewReader.findByProductId(productId, pageableRequest)
			.map(GetReviewResponse::from);
	}

	@Override
	@Transactional
	public void createReview(String productId, CreateReviewRequest createReviewRequest) {
		Product product = productValidator.validByProductId(productId);
		Review review = new Review(
			productId,
			createReviewRequest.userId(),
			createReviewRequest.content(),
			createReviewRequest.score(),
			shopAdminReader.findById(product.getShopAdminId()).isApproveStatus()
				? ReviewStatus.WAITING : ReviewStatus.APPROVED
		);
		reviewCreator.save(review);
	}

}
