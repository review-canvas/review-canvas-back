package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.romanticpipe.reviewcanvas.common.storage.S3Service;
import com.romanticpipe.reviewcanvas.common.util.TransactionUtils;
import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.domain.ReviewStatus;
import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewDetailResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.enumeration.ReplyFilter;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForShopAdmin;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForUser;
import com.romanticpipe.reviewcanvas.enumeration.Score;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ReviewErrorCode;
import com.romanticpipe.reviewcanvas.service.ProductService;
import com.romanticpipe.reviewcanvas.service.ReplyService;
import com.romanticpipe.reviewcanvas.service.ReviewService;
import com.romanticpipe.reviewcanvas.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ReviewUseCaseImpl implements ReviewUseCase {

	private final ProductService productService;
	private final ReviewService reviewService;
	private final ReplyService replyService;
	private final ProductUseCase productUseCase;
	private final TransactionUtils transactionUtils;
	private final UserService userService;
	private final S3Service s3Service;

	@Override
	public PageResponse<GetReviewDetailResponse> getReviewsForUser(String mallId, Long productNo,
		String memberId, PageableRequest pageableRequest, ReviewFilterForUser filter) {
		Product product = transactionUtils.executeInWriteTransaction(
			status -> productService.findProduct(mallId, productNo)
		).orElseGet(() -> productUseCase.createProduct(mallId, productNo));

		return transactionUtils.executeInReadTransaction(
			status -> reviewService.findAllByProductId(product.getId(), pageableRequest, filter)
				.map((review) -> GetReviewDetailResponse.from(review, review.getUser().getMemberId().equals(memberId)))
		);
	}

	@Override
	@Transactional(readOnly = true)
	public GetReviewDetailResponse getReviewForUser(Long reviewId, String memberId) {
		Review review = reviewService.validateUserInfoById(reviewId);
		return GetReviewDetailResponse.from(review, review.getUser().getMemberId().equals(memberId));
	}

	@Override
	@Transactional(readOnly = true)
	public PageResponse<GetReviewDetailResponse> getReviewsForDashboard(
		Long productId, PageableRequest pageable, EnumSet<ReviewFilterForShopAdmin> reviewFilters,
		EnumSet<Score> score, EnumSet<ReplyFilter> replyFilters
	) {
		return reviewService.findByProductId(productId, pageable, reviewFilters, score, replyFilters)
			.map((review) -> GetReviewDetailResponse.from(review, false));
	}

	@Override
	@Transactional
	public void updateReview(String mallId, String memberId, Long reviewId,
		UpdateReviewRequest updateReviewRequest, List<MultipartFile> reviewImages) {
		User user = userService.validByMemberIdAndMallId(memberId, mallId);
		Review review = reviewService.validByIdAndUserId(reviewId, user.getId());

		Product product = productService.findProduct(review.getProductId())
			.orElseThrow(() -> new BusinessException(ReviewErrorCode.PRODUCT_NOT_FOUND));
		String dirPath = "public-view/shop-admin" + product.getShopAdminId() + "/product-" + review.getProductId();
		s3Service.fileDelete(review.getImageVideoUrls(), dirPath);
		String savedFileNames = s3Service.uploadFiles(reviewImages, dirPath).stream()
			.reduce((fileName1, fileName2) -> fileName1 + "," + fileName2).orElse("");
		review.update(updateReviewRequest.score(), updateReviewRequest.content(), savedFileNames);
	}

	@Override
	@Transactional
	public void createReview(String mallId, Long productNo, CreateReviewRequest createReviewRequest,
		List<MultipartFile> reviewImages) {
		Product product = productService.findProduct(mallId, productNo)
			.orElseThrow(() -> new BusinessException(ReviewErrorCode.PRODUCT_NOT_FOUND));
		User user = userService.validByMemberIdAndMallId(createReviewRequest.memberId(), mallId);

		String saveImagePath = "public-view/shop-admin" + product.getShopAdminId() + "/product-" + product.getId();
		String savedFileNames = s3Service.uploadFiles(reviewImages, saveImagePath).stream()
			.reduce((fileName1, fileName2) -> fileName1 + "," + fileName2).orElse("");

		Review review = Review.builder()
			.productId(product.getId())
			.user(user)
			.content(createReviewRequest.content())
			.score(createReviewRequest.score())
			.status(ReviewStatus.APPROVED)
			.imageVideoUrls(savedFileNames)
			.build();
		reviewService.save(review);
	}

	@Override
	@Transactional
	public void deleteReviewByPublicView(String mallId, String memberId, long reviewId, LocalDateTime localDateTime) {
		User user = userService.validByMemberIdAndMallId(memberId, mallId);
		Review review = reviewService.validByIdAndUserId(reviewId, user.getId());
		/**
		 * TODO 댓글 먼저 삭제 후 리뷰 삭제
		 * replyService.deleteReply(reviewId);
		 */
		review.delete();
	}
}
