package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReplyByShopAdminRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReplyRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReplyByShopAdminRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReplyRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReplyForUserResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

	@Operation(summary = "특정 리뷰의 댓글 조회 API", description = "특정 리뷰의 댓글들을 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 댓글 조회가 완료되었습니다.")
	})
	@GetMapping("/reviews/{reviewId}/replies")
	ResponseEntity<SuccessResponse<List<GetReplyForUserResponse>>> getRepliesForUser(
		@PathVariable("reviewId") Long reviewId
	);

	@Operation(summary = "특정 댓글 조회 API", description = "특정 댓글을 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 댓글 단건 조회가 완료되었습니다.")
	})
	@GetMapping("/replies/{replyId}")
	ResponseEntity<SuccessResponse<GetReplyForUserResponse>> getReplyForUser(
		@PathVariable("reviewId") Long reviewId
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

	@Operation(summary = "특정 리뷰의 댓글 삭제 API", description = "특정 리뷰의 댓글을 삭제한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 댓글 삭제가 완료되었습니다.")
	})
	@DeleteMapping("/shop/{mallId}/users/{memberId}/replies/{replyId}")
	ResponseEntity<SuccessResponse<Void>> deleteReplyForUser(
		@PathVariable("mallId") String mallId,
		@PathVariable("memberId") String memberId,
		@PathVariable("replyId") Long replyId
	);

	@Operation(summary = "Shop Admin 리뷰 댓글 생성 API", description = "Shop Admin이 특정 리뷰의 댓글을 생성한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 댓글 생성이 완료되었습니다.")
	})
	@PostMapping("/shop-admin/reviews/{reviewId}/reply")
	ResponseEntity<SuccessResponse<Void>> createReplyForShopAdmin(
		@AuthInfo JwtInfo jwtInfo,
		@PathVariable("reviewId") Long reviewId,
		@RequestBody CreateReplyByShopAdminRequest createReplyByShopAdminRequest
	);

	@Operation(summary = "Shop Admin 리뷰 댓글 수정 API", description = "Shop Admin이 특정 리뷰의 댓글을 수정한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 댓글 수정이 완료되었습니다.")
	})
	@PatchMapping("/shop-admin/replies/{replyId}")
	ResponseEntity<SuccessResponse<Void>> updateReplyForShopAdmin(
		@AuthInfo JwtInfo jwtInfo,
		@PathVariable("replyId") Long replyId,
		@RequestBody UpdateReplyByShopAdminRequest updateReplyByShopAdminRequest
	);

	@Operation(summary = "Shop Admin 리뷰 댓글 삭제 API", description = "Shop Admin이 특정 리뷰의 댓글을 삭제한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 댓글 삭제가 완료되었습니다.")
	})
	@DeleteMapping("/shop-admin/replies/{replyId}")
	ResponseEntity<SuccessResponse<Void>> deleteReplyForShopAdmin(
		@AuthInfo JwtInfo jwtInfo,
		@PathVariable("replyId") Long replyId
	);
}
