package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.romanticpipe.reviewcanvas.admin.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.admin.service.ShopAdminService;
import com.romanticpipe.reviewcanvas.cafe24.product.Cafe24ProductClient;
import com.romanticpipe.reviewcanvas.cafe24.product.Cafe24ProductDto;
import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.domain.ReviewStatus;
import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewForUserResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilter;
import com.romanticpipe.reviewcanvas.service.ProductService;
import com.romanticpipe.reviewcanvas.service.ReviewService;
import com.romanticpipe.reviewcanvas.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ReviewUseCaseImpl implements ReviewUseCase {

	private final ShopAdminService shopAdminService;
	private final ProductService productService;
	private final ReviewService reviewService;
	private final UserService userService;
	private final Cafe24ProductClient cafe24ProductClient;
	private final TransactionTemplate writeTransactionTemplate;

	@Override
	public PageResponse<GetReviewForUserResponse> getReviewsForUser(String mallId, Long productNo,
		PageableRequest pageableRequest,
		ReviewFilter filter) {
		Product product = productService.findProduct(mallId, productNo)
			.orElseGet(() -> createProduct(mallId, productNo));

		return reviewService.findAllByProductId(product.getId(), pageableRequest, filter)
			.map(GetReviewForUserResponse::from);
	}

	@Override
	@Transactional(readOnly = true)
	public GetReviewForUserResponse getReviewForUser(Long reviewId) {
		return GetReviewForUserResponse.from(reviewService.validateUserInfoById(reviewId));
	}

	@Override
	@Transactional(readOnly = true)
	public PageResponse<GetReviewResponse> getReviewsByUserId(String userId, PageableRequest pageableRequest) {
		return reviewService.findByUserId(userId, pageableRequest)
			.map(GetReviewResponse::from);
	}

	@Override
	@Transactional
	public void updateReview(long reviewId, UpdateReviewRequest updateReviewRequest) {
		Review review = reviewService.validById(reviewId);
		review.setScore(updateReviewRequest.score());
		review.setContent(updateReviewRequest.content());
	}

	@Override
	@Transactional
	public void createReview(Long productId, CreateReviewRequest createReviewRequest) {
		Product product = productService.validByProductId(productId);
		User user = userService.validByUserId(createReviewRequest.memberId());
		ShopAdmin shopAdmin = shopAdminService.validById(product.getShopAdminId());
		// TODO: 프론트로부터 memberId를 받아 user를 조회하여 userId를 가져온다.
		Review review = new Review(productId,
			user.getId(),
			createReviewRequest.content(),
			createReviewRequest.score(),
			ReviewStatus.APPROVED,
			""
		);
		reviewService.save(review);
	}

	private Product createProduct(String mallId, Long productNo) {
		Cafe24ProductDto cafe24ProductDto = cafe24ProductClient.getProduct(mallId, productNo);
		cafe24ProductDto.validateIsFullContent(mallId, productNo);

		return writeTransactionTemplate.execute(status -> {
			ShopAdmin shopAdmin = shopAdminService.validByMallId(mallId);
			Product product = new Product(productNo, cafe24ProductDto.product().productName(), shopAdmin.getId());
			return productService.save(product);
		});
	}
}
