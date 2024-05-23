package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.ReplyUseCase;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReplyRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReplyForUserResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReplyController implements ReplyApi {

	private final ReplyUseCase replyUseCase;

	@Override
	@PostMapping("/reviews/{reviewId}/reply")
	public ResponseEntity<SuccessResponse<Void>> createReplyForUser(
		@PathVariable Long reviewId,
		@RequestBody CreateReplyRequest createReplyRequest) {
		replyUseCase.createReplyForUser(reviewId, createReplyRequest);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/reviews/{reviewId}/replies")
	public ResponseEntity<SuccessResponse<List<GetReplyForUserResponse>>> getReplyForUser(
		@PathVariable("reviewId") Long reviewId) {
		return SuccessResponse.of(
			replyUseCase.getReplyForUser(reviewId)
		).asHttp(HttpStatus.OK);
	}

}
