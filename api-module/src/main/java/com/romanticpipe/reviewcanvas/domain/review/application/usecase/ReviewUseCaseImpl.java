package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.admin.service.ShopAdminService;
import com.romanticpipe.reviewcanvas.config.TransactionUtils;
import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.domain.ReviewStatus;
import com.romanticpipe.reviewcanvas.domain.ReviewType;
import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewByShopAdminRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.FileContentsResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewDetailResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.enumeration.ReplyFilter;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForShopAdmin;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForUser;
import com.romanticpipe.reviewcanvas.enumeration.ReviewPeriod;
import com.romanticpipe.reviewcanvas.enumeration.Score;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.CommonErrorCode;
import com.romanticpipe.reviewcanvas.exception.ProductNotFoundException;
import com.romanticpipe.reviewcanvas.exception.ReviewNotMatchAdminException;
import com.romanticpipe.reviewcanvas.service.ProductService;
import com.romanticpipe.reviewcanvas.service.ReplyService;
import com.romanticpipe.reviewcanvas.service.ReviewLikeService;
import com.romanticpipe.reviewcanvas.service.ReviewService;
import com.romanticpipe.reviewcanvas.service.UserService;
import com.romanticpipe.reviewcanvas.storage.FileExtensionUtils;
import com.romanticpipe.reviewcanvas.storage.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class ReviewUseCaseImpl implements ReviewUseCase {

	private final ShopAdminService shopAdminService;
	private final ProductService productService;
	private final ReviewService reviewService;
	private final ReplyService replyService;
	private final ProductUseCase productUseCase;
	private final TransactionUtils transactionUtils;
	private final UserService userService;
	private final S3Service s3Service;
	private final ReviewLikeService reviewLikeService;

	@Override
	public PageResponse<GetReviewDetailResponse> getReviewsForUser(String mallId, Long productNo,
																   String memberId, PageableRequest pageableRequest,
																   ReviewFilterForUser filter) {
		Product product = transactionUtils.executeInWriteTransaction(
			status -> productService.findProduct(mallId, productNo)
		).orElseGet(() -> productUseCase.createProduct(mallId, productNo));

		return transactionUtils.executeInReadTransaction(status -> {
			Optional<Long> requestUserId = this.getRequestUserId(memberId, mallId);
			return reviewService.findAllByProductId(product.getId(), pageableRequest, filter)
				.map(review -> this.convertGetReviewDetailResponse(review, requestUserId));
		});
	}

	@Override
	@Transactional(readOnly = true)
	public PageResponse<GetReviewDetailResponse> getReviewsInMyPage(String mallId, String memberId,
																	PageableRequest pageable,
																	ReviewFilterForUser filter) {
		User requestUser = userService.validByMemberIdAndMallId(memberId, mallId);
		return reviewService.getReviewsInMyPage(requestUser.getId(), pageable, filter)
			.map(review -> this.convertGetReviewDetailResponse(review, Optional.of(requestUser.getId())));
	}

	@Override
	@Transactional(readOnly = true)
	public GetReviewDetailResponse getReviewForUser(Long reviewId, String mallId, String memberId) {
		Review review = reviewService.validateById(reviewId);
		Optional<Long> requestUserId = this.getRequestUserId(memberId, mallId);
		return this.convertGetReviewDetailResponse(review, requestUserId);
	}

	@Override
	@Transactional(readOnly = true)
	public PageResponse<GetReviewDetailResponse> getReviewsForDashboard(
		Integer shopAdminId, Long productId, PageableRequest pageable, ReviewPeriod reviewPeriod,
		EnumSet<ReviewFilterForShopAdmin> reviewFilters, EnumSet<Score> score, EnumSet<ReplyFilter> replyFilters
	) {
		return reviewService.findAllByProductId(shopAdminId, productId, pageable, reviewPeriod, reviewFilters, score,
				replyFilters)
			.map(review -> this.convertGetReviewDetailResponse(review, shopAdminId));
	}

	@Override
	public PageResponse<GetReviewDetailResponse> getProductReviewsInMyPage(String mallId, String memberId,
																		   Long productNo, PageableRequest pageable,
																		   ReviewFilterForUser filter) {
		Product product = transactionUtils.executeInWriteTransaction(
			status -> productService.findProduct(mallId, productNo)
		).orElseGet(() -> productUseCase.createProduct(mallId, productNo));

		return transactionUtils.executeInReadTransaction(
			status -> {
				User requestUser = userService.validByMemberIdAndMallId(memberId, mallId);
				return reviewService.getProductReviewsInMyPage(requestUser.getId(), product.getId(), pageable, filter)
					.map(review -> this.convertGetReviewDetailResponse(review, Optional.of(requestUser.getId())));
			});
	}

	@Override
	@Transactional
	public void updateReview(String mallId, String memberId, Long reviewId,
							 UpdateReviewRequest updateReviewRequest, List<MultipartFile> reviewFiles) {
		User user = userService.validByMemberIdAndMallId(memberId, mallId);
		Review review = reviewService.validByIdAndUserId(reviewId, user.getId());
		Product product = review.getProduct();
		ReviewType reviewType = this.getReviewType(review.getReviewType(), reviewFiles);

		String savedFileKeys = this.uploadReviewFiles(reviewFiles, product);
		if (StringUtils.hasText(savedFileKeys)) {
			review.update(updateReviewRequest.score(), updateReviewRequest.content(), savedFileKeys, reviewType);
		} else {
			review.update(updateReviewRequest.score(), updateReviewRequest.content(), review.getImageVideoUrls(),
				reviewType);
		}
	}

	@Override
	@Transactional
	public void createReview(String mallId, Long productNo, CreateReviewRequest createReviewRequest,
							 List<MultipartFile> reviewFiles) {
		Product product = productService.findProduct(mallId, productNo)
			.orElseThrow(ProductNotFoundException::new);
		User user = userService.validByMemberIdAndMallId(createReviewRequest.memberId(), mallId);
		ReviewType reviewType = this.getReviewType(ReviewType.TEXT, reviewFiles);

		String savedFileKeys = this.uploadReviewFiles(reviewFiles, product);
		Review review = Review.builder()
			.product(product)
			.user(user)
			.content(createReviewRequest.content())
			.score(createReviewRequest.score())
			.status(ReviewStatus.APPROVED)
			.imageVideoUrls(savedFileKeys)
			.reviewType(reviewType)
			.build();
		reviewService.save(review);
	}

	@Override
	@Transactional
	public void deleteReviewByPublicView(String mallId, String memberId, Long reviewId) {
		User user = userService.validByMemberIdAndMallId(memberId, mallId);
		Review review = reviewService.validByIdAndUserId(reviewId, user.getId());
		replyService.deleteAllReplyByReviewId(review.getId());
		review.delete();
	}

	@Override
	@Transactional
	public void createReviewByShopAdmin(Integer shopAdminId, Long productId,
										CreateReviewByShopAdminRequest createReviewByShopAdminRequest,
										List<MultipartFile> reviewFiles) {
		shopAdminService.validateById(shopAdminId);
		Product product = productService.validateById(productId);
		ReviewType reviewType = this.getReviewType(ReviewType.TEXT, reviewFiles);

		String savedFileKeys = this.uploadReviewFiles(reviewFiles, product);
		Review review = Review.builder()
			.product(product)
			.content(createReviewByShopAdminRequest.content())
			.score(createReviewByShopAdminRequest.score())
			.status(ReviewStatus.APPROVED)
			.imageVideoUrls(savedFileKeys)
			.reviewType(reviewType)
			.shopAdminId(shopAdminId)
			.build();
		reviewService.save(review);
	}

	@Override
	@Transactional
	public void deleteReviewByShopAdmin(Integer shopAdminId, Long reviewId) {
		shopAdminService.validateById(shopAdminId);
		Review review = reviewService.validById(reviewId);
		if (!review.isThisShopReview(shopAdminId)) {
			throw new ReviewNotMatchAdminException();
		}

		replyService.deleteAllReplyByReviewId(review.getId());
		review.delete();
	}

	@Override
	@Transactional
	public void updateReviewByShopAdmin(Integer shopAdminId, Long reviewId,
										UpdateReviewRequest updateReviewRequest, List<MultipartFile> reviewFiles) {
		shopAdminService.validateById(shopAdminId);
		Review review = reviewService.validById(reviewId);
		if (!review.isThisShopReview(shopAdminId)) {
			throw new ReviewNotMatchAdminException();
		}
		ReviewType reviewType = this.getReviewType(review.getReviewType(), reviewFiles);

		String savedFileKeys = this.uploadReviewFiles(reviewFiles, review.getProduct());
		if (StringUtils.hasText(savedFileKeys)) {
			review.update(updateReviewRequest.score(), updateReviewRequest.content(), savedFileKeys, reviewType);
		} else {
			review.update(updateReviewRequest.score(), updateReviewRequest.content(), review.getImageVideoUrls(),
				reviewType);
		}
	}

	private String uploadReviewFiles(List<MultipartFile> reviewFiles, Product product) {
		String reviewDirPath = s3Service.getReviewDirPath(product.getShopAdminId(), product.getId());
		return s3Service.uploadFiles(reviewFiles, reviewDirPath).stream()
			.reduce((fileName1, fileName2) -> fileName1 + "," + fileName2).orElse("");
	}

	private ReviewType getReviewType(ReviewType defaultReviewType, List<MultipartFile> reviewFiles) {
		ReviewType reviewType = defaultReviewType;
		for (MultipartFile reviewImage : reviewFiles) {
			String filename = reviewImage.getOriginalFilename();
			if (FileExtensionUtils.isImage(filename)) {
				reviewType = reviewType == ReviewType.VIDEO ? ReviewType.VIDEO : ReviewType.PHOTO;
			} else if (FileExtensionUtils.isVideo(filename)) {
				reviewType = ReviewType.VIDEO;
			} else {
				throw new BusinessException(CommonErrorCode.INCOMPATIBLE_FORMAT_TYPE);
			}
		}
		return reviewType;
	}

	private GetReviewDetailResponse convertGetReviewDetailResponse(Review review, Optional<Long> requestUserId) {
		int reviewLikeCount = reviewLikeService.getReviewLikeCount(review.getId());
		boolean isRequestUserLikeThisReview = requestUserId
			.map(userId -> reviewLikeService.isUserLikeThisReview(review.getId(), userId))
			.orElse(false);

		if (review.getReviewType() == ReviewType.TEXT) {
			return GetReviewDetailResponse.forUser(review, requestUserId, FileContentsResponse.empty(),
				reviewLikeCount, isRequestUserLikeThisReview);
		}

		return GetReviewDetailResponse.forUser(review, requestUserId, this.getFileContentsResponse(review),
			reviewLikeCount, isRequestUserLikeThisReview);
	}

	private GetReviewDetailResponse convertGetReviewDetailResponse(Review review, Integer requestShopAdminId) {
		int reviewLikeCount = reviewLikeService.getReviewLikeCount(review.getId());
		boolean isLikeThisReview = reviewLikeService.isShopAdminLikeThisReview(review.getId(), requestShopAdminId);

		if (review.getReviewType() == ReviewType.TEXT) {
			return GetReviewDetailResponse.forShopAdmin(review, FileContentsResponse.empty(), reviewLikeCount,
				isLikeThisReview);
		}

		return GetReviewDetailResponse.forShopAdmin(review, this.getFileContentsResponse(review), reviewLikeCount,
			isLikeThisReview);
	}

	private FileContentsResponse getFileContentsResponse(Review review) {
		List<String> objectKeys = Arrays.stream(review.getImageVideoUrls().split(",")).toList();
		List<String> reviewFileUrls = s3Service.getReviewFileUrls(objectKeys);
		List<String> reviewResizeImageUrls = s3Service.getReviewResizeImageUrls(objectKeys);
		return new FileContentsResponse(reviewFileUrls, reviewResizeImageUrls);
	}

	private Optional<Long> getRequestUserId(String memberId, String mallId) {
		if (StringUtils.hasText(memberId)) {
			return Optional.of(userService.validByMemberIdAndMallId(memberId, mallId).getId());
		}
		return Optional.empty();
	}
}
