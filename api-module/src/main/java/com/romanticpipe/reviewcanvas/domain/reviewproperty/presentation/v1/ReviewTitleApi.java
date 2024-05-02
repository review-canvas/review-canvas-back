package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateReviewTitleRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewTitleResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ReviewTitle", description = "리뷰 제목 디자인 API")
public interface ReviewTitleApi {

	@Operation(summary = "리뷰 제목 디자인 수정 API", description = "리뷰 제목의 디자인 속성값을 수정한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 제목 디자인 수정이 완료되었습니다.")
	})
	@PatchMapping("/shop-admin/review-title")
	ResponseEntity<SuccessResponse<Void>> updateReviewTitle(
		@AuthInfo JwtInfo jwtInfo, @RequestBody UpdateReviewTitleRequest updateReviewTitleRequest
	);

	@Operation(summary = "리뷰 제목 디자인 초기화 API", description = "리뷰 제목의 디자인 속성값을 초기화한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 제목 디자인 초기화가 완료되었습니다.")
	})
	@PatchMapping("/shop-admin/review-title/reset")
	ResponseEntity<SuccessResponse<Void>> initializeReviewTitle(
		@AuthInfo JwtInfo jwtInfo
	);

	@Operation(summary = "리뷰 제목 디자인 조회 API", description = "리뷰 제목의 디자인 속성값을 조회한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 제목 디자인 조회가 완료되었습니다.")
	})
	@GetMapping("/shop-admin/review-title")
	ResponseEntity<SuccessResponse<GetReviewTitleResponse>> getReviewTitle(
		@AuthInfo JwtInfo jwtInfo
	);
}
