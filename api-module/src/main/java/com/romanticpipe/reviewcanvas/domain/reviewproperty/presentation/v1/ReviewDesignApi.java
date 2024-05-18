package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateDesignViewRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateDesignWriteRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewDesignViewResponse;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewDesignWriteResponse;
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

@Tag(name = "ReviewDesignApi", description = "리뷰 디자인 보기/쓰기 API")
public interface ReviewDesignApi {

	@Operation(summary = "리뷰 디자인 보기 조회 API", description = "저장되어 있는 리뷰 디자인 보기 설정 값을 조회한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 디자인 보기 설정값을 반환했습니다.")
	})
	@GetMapping("/shop-admin/review-design-view")
	ResponseEntity<SuccessResponse<GetReviewDesignViewResponse>> getReviewDesignView(@AuthInfo JwtInfo jwtInfo);

	@Operation(summary = "리뷰 디자인 보기 수정  API", description = "입력받은 값으로 리뷰 디자인 보기 설정값을 수정한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 디자인 보기 설정값을 수정했습니다.")
	})
	@PatchMapping("/shop-admin/review-design-view")
	ResponseEntity<SuccessResponse<Void>> updateReviewDesignView(
		@AuthInfo JwtInfo jwtInfo,
		@Valid @RequestBody UpdateDesignViewRequest updateDesignViewRequest
	);

	@Operation(summary = "리뷰 디자인 보기 초기화 API", description = "저장되어 있는 리뷰 디자인 보기 설정 값을 초기화한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 디자인 보기 설정값을 초기화 했습니다.")
	})
	@PatchMapping("/shop-admin/review-design-view/reset")
	ResponseEntity<SuccessResponse<Void>> resetReviewDesignView(
		@AuthInfo JwtInfo jwtInfo
	);

	@Operation(summary = "리뷰 디자인 쓰기 조회 API", description = "저장되어 있는 리뷰 디자인 쓰기 설정 값을 조회한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 디자인 쓰기 설정값을 조회 했습니다.")
	})
	@GetMapping("/shop-admin/review-design-write")
	ResponseEntity<SuccessResponse<GetReviewDesignWriteResponse>> getReviewDesignWrite(@AuthInfo JwtInfo jwtInfo);

	@Operation(summary = "리뷰 디자인 쓰기 수정  API", description = "입력받은 값으로 리뷰 디자인 쓰기 설정값을 수정한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 디자인 쓰기 설정값을 수정했습니다.")
	})
	@PatchMapping("/shop-admin/review-design-write")
	ResponseEntity<SuccessResponse<Void>> updateReviewDesignView(
		@AuthInfo JwtInfo jwtInfo,
		@Valid @RequestBody UpdateDesignWriteRequest updateDesignWriteRequest
	);

	@Operation(summary = "리뷰 디자인 쓰기 초기화 API", description = "저장되어 있는 리뷰 디자인 쓰기 설정 값을 초기화한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 디자인 쓰기 설정값을 초기화 했습니다.")
	})
	@PatchMapping("/shop-admin/review-design-write/reset")
	ResponseEntity<SuccessResponse<Void>> resetReviewDesignWrite(@AuthInfo JwtInfo jwtInfo);
}
