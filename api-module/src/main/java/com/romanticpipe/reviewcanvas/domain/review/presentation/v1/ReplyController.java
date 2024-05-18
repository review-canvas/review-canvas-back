package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.ReplyUseCase;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReplyRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReplyRequest;

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
	@PostMapping("/replies/{replyId}")
	public ResponseEntity<SuccessResponse<Void>> updateReplyForUser(
		@PathVariable("replyId") Long replyId,
		@RequestBody UpdateReplyRequest updateReplyRequest) {
		replyUseCase.updateReplyForUser(replyId, updateReplyRequest);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}
}
