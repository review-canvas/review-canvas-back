package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateColumnRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewColumnResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "ReviewColumn", description = "리뷰 Column 디자인 API")
public interface ReviewColumnApi {

	@Operation(summary = "Column 디자인 조회 API", description = "Column 디자인 속성을 조회한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 Column 디자인 조회를 완료했습니다.")
	})
	@GetMapping("/shop-admin/review-column")
	ResponseEntity<SuccessResponse<GetReviewColumnResponse>> getColumn(
		@AuthInfo JwtInfo jwtInfo
	);

	@Operation(summary = "Column 디자인 저장 API", description = "Column 디자인 속성을 저장한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 Column 디자인 조회를 완료했습니다.")
	})
	@PatchMapping("/shop-admin/review-column")
	ResponseEntity<SuccessResponse<Void>> updateColumn(
		@AuthInfo JwtInfo jwtInfo,
		@RequestBody UpdateColumnRequest updateColumnRequest
	);

	@Operation(summary = "Column 디자인 초기화 API", description = "Column 디자인 속성을 초기화한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 Column 디자인 조회를 완료했습니다.")
	})
	@PatchMapping("/shop-admin/review-column/reset")
	ResponseEntity<SuccessResponse<Void>> resetColumn(
		@AuthInfo JwtInfo jwtInfo
	);
}
