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
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewForUserResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.dto.ReviewInfo;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilter;
import com.romanticpipe.reviewcanvas.service.ProductService;
import com.romanticpipe.reviewcanvas.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ReviewUseCaseImpl implements ReviewUseCase {

	private final ShopAdminService shopAdminService;
	private final ProductService productService;
	private final ReviewService reviewService;
	private final Cafe24ProductClient cafe24ProductClient;
	private final TransactionTemplate writeTransactionTemplate;

	@Override
	//TODO: 이거 왜 transactional 없냐
	public PageResponse<GetReviewForUserResponse> getReviewsForUser(String mallId, Long productNo,
		String memberId, PageableRequest pageableRequest,
		ReviewFilter filter) {
		Product product = productService.findProduct(mallId, productNo)
			.orElseGet(() -> createProduct(mallId, productNo));

		return reviewService.findAllByProductId(product.getId(), pageableRequest, filter)
			.map((reviewInfo) -> GetReviewForUserResponse.from(reviewInfo, reviewInfo.getMemberId().equals(memberId)));
	}

	@Override
	@Transactional(readOnly = true)
	public GetReviewForUserResponse getReviewForUser(Long reviewId, String memberId) {
		ReviewInfo reviewInfo = reviewService.validateUserInfoById(reviewId);
		return GetReviewForUserResponse.from(reviewService.validateUserInfoById(reviewId),
			reviewInfo.getMemberId().equals(memberId));
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
	public void createReview(String productId, CreateReviewRequest createReviewRequest) {
		Product product = productService.validByProductId(productId);
		// TODO: product가  mallId와 productNo로 상품을 생성하는 로직을 추가하도록 변경해야 함.
		//		Product product = productReader.findByMallIdAndProductNo(mallId, productNo)
		//			.orElseGet(() -> createProduct(mallId, productNo));
		ShopAdmin shopAdmin = shopAdminService.validById(product.getShopAdminId());
		// TODO: 프론트로부터 memberId를 받아 user를 조회하여 userId를 가져온다.
		//		Review review = new Review(
		//			null,
		//			null,
		//			createReviewRequest.content(),
		//			createReviewRequest.score(),
		//			shopAdmin.isApproveStatus()
		//				? ReviewStatus.WAITING : ReviewStatus.APPROVED
		//		);
		//		reviewCreator.save(review);
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
