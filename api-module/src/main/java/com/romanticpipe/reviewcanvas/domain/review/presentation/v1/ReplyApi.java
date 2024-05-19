package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReplyRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReplyRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Reply", description = "댓글 API")
public interface ReplyApi {

	@Operation(summary = "리뷰 댓글 생성 API", description = "특정 리뷰의 댓글을 생성한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 댓글 생성이 완료되었습니다.")
	})
	@PostMapping("/reviews/{reviewId}/reply")
	ResponseEntity<SuccessResponse<Void>> createReplyForUser(
		@PathVariable("reviewId") Long reviewId,
		@RequestBody CreateReplyRequest createReplyRequest
	);

	@Operation(summary = "리뷰 댓글 수정 API", description = "리뷰의 자신의 댓글을 수정한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 댓글 수정이 완료되었습니다.")
	})
	@PostMapping("/replies/{replyId}")
	ResponseEntity<SuccessResponse<Void>> updateReplyForUser(
		@PathVariable("replyId") Long replyId,
		@Valid @RequestBody UpdateReplyRequest updateReplyRequest
	);
}
