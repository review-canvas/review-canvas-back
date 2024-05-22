package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.ReviewUseCase;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewDetailResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.enumeration.ReplyFilter;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForShopAdmin;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForUser;
import com.romanticpipe.reviewcanvas.enumeration.ReviewSort;
import com.romanticpipe.reviewcanvas.enumeration.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.EnumSet;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class ReviewController implements ReviewApi {

	private final ReviewUseCase reviewUseCase;

	@Override
	@GetMapping("/shop/{mallId}/products/{productNo}/reviews")
	public ResponseEntity<SuccessResponse<PageResponse<GetReviewDetailResponse>>> getReviewsForUser(
		@PathVariable("mallId") String mallId,
		@PathVariable("productNo") Long productNo,
		@RequestParam(value = "size", required = false, defaultValue = "10") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "sort", required = false, defaultValue = "LATEST") ReviewSort sort,
		@RequestParam(name = "filter", required = false, defaultValue = "ALL") ReviewFilterForUser filter) {
		return SuccessResponse.of(
			reviewUseCase.getReviewsForUser(mallId, productNo, PageableRequest.of(page, size, sort), filter)
		).asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<SuccessResponse<GetReviewDetailResponse>> getReviewForUser(
		@PathVariable Long reviewId) {
		return SuccessResponse.of(
			reviewUseCase.getReviewForUser(reviewId)
		).asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/products/{productId}/reviews")
	public ResponseEntity<SuccessResponse<PageResponse<GetReviewDetailResponse>>> getReviewsForDashboard(
		@PathVariable("productId") Long productId,
		@RequestParam(value = "size", required = false, defaultValue = "10") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "sort", required = false, defaultValue = "LATEST") ReviewSort sort,
		@RequestParam(name = "reviewFilters", required = false, defaultValue = "PHOTO,VIDEO,TEXT")
		EnumSet<ReviewFilterForShopAdmin> reviewFilters,
		@RequestParam(name = "score", required = false, defaultValue = "ONE,TWO,THREE,FOUR,FIVE") EnumSet<Score> score,
		@RequestParam(name = "replyFilters", required = false, defaultValue = "REPLIED,NOT_REPLIED")
		EnumSet<ReplyFilter> replyFilters
	) {
		PageableRequest pageable = PageableRequest.of(page, size, sort);
		return SuccessResponse.of(
			reviewUseCase.getReviewsForDashboard(productId, pageable, reviewFilters, score, replyFilters)
		).asHttp(HttpStatus.OK);
	}

	@Override
	@PostMapping(value = "/shop/{mallId}/products/{productNo}/review", consumes = "multipart/form-data")
	public ResponseEntity<SuccessResponse<Void>> createReview(
		@PathVariable("mallId") String mallId,
		@PathVariable("productNo") Long productId,
		@RequestPart CreateReviewRequest createReviewRequest,
		@RequestPart(required = false) List<MultipartFile> reviewImages) {
		if (reviewImages == null) {
			reviewImages = List.of();
		}
		reviewUseCase.createReview(mallId, productId, createReviewRequest, reviewImages);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@PatchMapping(value = "/shop/{mallId}/users/{memberId}/reviews/{reviewId}", consumes = "multipart/form-data")
	public ResponseEntity<SuccessResponse<Void>> updateReview(
		@PathVariable("mallId") String mallId,
		@PathVariable("memberId") String memberId,
		@PathVariable("reviewId") long reviewId,
		UpdateReviewRequest updateReviewRequest,
		List<MultipartFile> reviewImages) {
		if (reviewImages == null) {
			reviewImages = List.of();
		}
		reviewUseCase.updateReview(mallId, memberId, reviewId, updateReviewRequest, reviewImages);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

}
