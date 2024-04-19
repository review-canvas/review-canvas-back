package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.domain.ReviewStatus;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.service.ProductValidator;
import com.romanticpipe.reviewcanvas.service.ReviewCreator;
import com.romanticpipe.reviewcanvas.service.ReviewReader;
import com.romanticpipe.reviewcanvas.service.ReviewValidator;
import com.romanticpipe.reviewcanvas.service.ShopAdminValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
class ReviewUseCaseImpl implements ReviewUseCase {

	private final ShopAdminValidator shopAdminValidator;
	private final ProductValidator productValidator;
	private final ReviewReader reviewReader;
	private final ReviewCreator reviewCreator;
	private final ReviewValidator reviewValidator;

	@Override
	@Transactional(readOnly = true)
	public PageResponse<GetReviewResponse> getReviewsByProductId(String productId, PageableRequest pageableRequest) {
		return reviewReader.findByProductId(productId, pageableRequest)
			.map(GetReviewResponse::from);
	}

	@Override
	@Transactional(readOnly = true)
	public PageResponse<GetReviewResponse> getReviewsByUserId(String userId, PageableRequest pageableRequest) {
		return reviewReader.findByUserId(userId, pageableRequest)
			.map(GetReviewResponse::from);
	}

	@Override
	@Transactional
	public void updateReview(long reviewId, UpdateReviewRequest updateReviewRequest) {
		Review review = reviewValidator.validById(reviewId);
		review.setScore(updateReviewRequest.score());
		review.setContent(updateReviewRequest.content());
	}

	@Override
	@Transactional
	public void createReview(String productId, CreateReviewRequest createReviewRequest) {
		Product product = productValidator.validByProductId(productId);
		ShopAdmin shopAdmin = shopAdminValidator.validById(product.getShopAdminId());
		// TODO: 프론트로부터 mallId, productNo product를 조회하여 productId를 가져온다.
		// TODO: 프론트로부터 memberId를 받아 user를 조회하여 userId를 가져온다.
		Review review = new Review(
			null,
			null,
			createReviewRequest.content(),
			createReviewRequest.score(),
			shopAdmin.isApproveStatus()
				? ReviewStatus.WAITING : ReviewStatus.APPROVED
		);
		reviewCreator.save(review);
	}

}
