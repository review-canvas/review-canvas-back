package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.ReviewLikeUseCase;

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
}
