package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.reponse.GetReviewContainerResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

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
}
