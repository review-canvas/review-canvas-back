package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateContainerRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewContainerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "ReviewContainer", description = "리뷰 Container API")
public interface ReviewContainerApi {

	@Operation(summary = "리뷰 Container 디자인 조회 API", description = "저장되어 있는 리뷰 Container 디자인 설정 값을 조회한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 Container 디자인 조회가 완료되었습니다.")
	})
	@GetMapping("/shop-admin/review-container")
	ResponseEntity<SuccessResponse<GetReviewContainerResponse>> getReviewContainer(
		@AuthInfo JwtInfo jwtInfo
	);

	@Operation(summary = "리뷰 Container 디자인 수정 API", description = "저장되어 있는 리뷰 Container 디자인 설정 값을 수정한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 Container 디자인 수정이 완료되었습니다.")
	})
	@PatchMapping("/shop-admin/review-container")
	ResponseEntity<SuccessResponse<Void>> updateReviewContainer(
		@AuthInfo JwtInfo jwtInfo,
		@Valid @RequestBody UpdateContainerRequest updateContainerRequest
	);

	@Operation(summary = "리뷰 Container 디자인 초기화 API", description = "저장되어 있는 리뷰 Container 디자인 설정 값을 초기화한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 Container 디자인 초기화가 완료되었습니다.")
	})
	@PatchMapping("/shop-admin/review-container/reset")
	ResponseEntity<SuccessResponse<Void>> resetReviewContainer(
		@AuthInfo JwtInfo jwtInfo
	);
}
