package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewPropertyResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

public interface ReviewPropertyApi {

	@Operation(summary = "리뷰 디자인 전체 조회 API", description = "ShopAdmin의 리뷰 디자인 속성 전체를 불러온다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 속성 조회가 완료되었습니다.")
	})
	@GetMapping("/reviews/review-properties")
	ResponseEntity<SuccessResponse<GetReviewPropertyResponse>> getAllReviewProperty(
		@AuthInfo JwtInfo jwtInfo
	);
}
