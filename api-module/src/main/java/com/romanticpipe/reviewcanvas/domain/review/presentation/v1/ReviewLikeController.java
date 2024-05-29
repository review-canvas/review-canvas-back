package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.ReviewLikeUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewLikeController implements ReviewLikeApi {

	private final ReviewLikeUseCase reviewLikeUseCase;

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
