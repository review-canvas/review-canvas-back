package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.cafe24.product.Cafe24ProductClient;
import com.romanticpipe.reviewcanvas.cafe24.product.Cafe24ProductDto;
import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.domain.ReviewStatus;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.service.ProductCreator;
import com.romanticpipe.reviewcanvas.service.ProductReader;
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
	private final ProductCreator productCreator;
	private final ProductValidator productValidator;
	private final ProductReader productReader;
	private final ReviewReader reviewReader;
	private final ReviewCreator reviewCreator;
	private final ReviewValidator reviewValidator;
	private final Cafe24ProductClient cafe24ProductClient;

	@Override
	public PageResponse<GetReviewResponse> getReviewsForUser(String mallId, Long productNo,
															 PageableRequest pageableRequest) {
		Product product = productValidator.validateByMallIdAndProductNo(mallId, productNo);
		return reviewReader.findByProductId(product.getId(), pageableRequest)
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
		// TODO: product가  mallId와 productNo로 상품을 생성하는 로직을 추가하도록 변경해야 함.
//		Product product = productReader.findByMallIdAndProductNo(mallId, productNo)
//			.orElseGet(() -> createProduct(mallId, productNo));
		ShopAdmin shopAdmin = shopAdminValidator.validById(product.getShopAdminId());
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

	private Product createProduct(String mallId, Long productNo) {
		Cafe24ProductDto cafe24ProductDto = cafe24ProductClient.getProduct(mallId, productNo);
		cafe24ProductDto.validateCafe24Product(mallId, productNo);
		ShopAdmin shopAdmin = shopAdminValidator.validByMallId(mallId);

		Product product = new Product(productNo, cafe24ProductDto.getProductName(), shopAdmin.getId());
		return productCreator.save(product);
	}
}
