package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.ReviewLikeUseCase;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewLikeRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewLikeController implements ReviewLikeApi {

	private final ReviewLikeUseCase reviewLikeUseCase;

	@Override
	@GetMapping("/reviews/{reviewId}/like/count")
	public ResponseEntity<SuccessResponse<Map<String, Integer>>> getReviewLikeCount(
		@PathVariable("reviewId") Long reviewId
	) {
		int likeCount = reviewLikeUseCase.getReviewLikeCount(reviewId);
		return SuccessResponse.of(
			Map.of("count", likeCount)
		).asHttp(HttpStatus.OK);
	}

	@Override
	@PostMapping("/reviews/{reviewId}/like/")
	public ResponseEntity<SuccessResponse<Void>> createReviewLikeForUser(
		@PathVariable("reviewId") Long reviewId,
		@Valid @RequestBody CreateReviewLikeRequest createReviewLikeRequest
	) {
		reviewLikeUseCase.createReviewLikeForUser(reviewId, createReviewLikeRequest);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@PostMapping("/shop-admin/reviews/{reviewId}/like")
	public ResponseEntity<SuccessResponse<Void>> createReviewLikeForShopAdmin(
		@AuthInfo JwtInfo jwtInfo,
		@PathVariable("reviewId") Long reviewId
	) {
		reviewLikeUseCase.createReviewLikeForShopAdmin(jwtInfo.adminId(), reviewId);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/shop/{mallId}/users/{memberId}/reviews/{reviewId}/like")
	public ResponseEntity<SuccessResponse<Void>> deleteReviewLikeForUser(
		@PathVariable("mallId") String mallId,
		@PathVariable("memberId") String memberId,
		@PathVariable("reviewId") long reviewId
	) {
		reviewLikeUseCase.deleteReviewLikeForUser(mallId, memberId, reviewId);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/shop-admin/reviews/{reviewId}/like")
	public ResponseEntity<SuccessResponse<Void>> deleteReviewLikeForShopAdmin(
		@AuthInfo JwtInfo jwtInfo,
		@PathVariable("reviewId") Long reviewId
	) {
		reviewLikeUseCase.deleteReviewLikeForShopAdmin(jwtInfo.adminId(), reviewId);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}
}
