package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateLayoutRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.ReviewLayoutResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

public interface ReviewLayoutApi {

	@Operation(summary = "Layout 디자인 수정 API", description = "Layout 디자인 속성을 수정한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 Layout 디자인 수정을 완료했습니다.")
	})
	@PatchMapping("/shop-admin/review-layout")
	ResponseEntity<SuccessResponse<Void>> updateLayout(
		@AuthInfo JwtInfo jwtInfo,
		@RequestBody UpdateLayoutRequest updateLayoutRequest
	);

	@Operation(summary = "Layout 디자인 속성 조회 API", description = "Layout 디자인 속성을 조회한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 Layout 디자인 속성 조회를 완료했습니다.")
	})
	@GetMapping("/shop-admin/review-layout")
	ResponseEntity<SuccessResponse<ReviewLayoutResponse>> getReviewLayout(@AuthInfo JwtInfo jwtInfo);

	@Operation(summary = "Layout 디자인 속성 초기화 API", description = "Layout 디자인 속성을 초기화한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 Layout 디자인 속성을 초기화했습니다.")
	})
	@PatchMapping("/shop-admin/review-layout/initialize")
	ResponseEntity<SuccessResponse<Void>> initializeReviewLayout(@AuthInfo JwtInfo jwtInfo);
}
