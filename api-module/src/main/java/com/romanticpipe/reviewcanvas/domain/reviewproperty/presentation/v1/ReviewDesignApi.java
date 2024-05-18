package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewDesignViewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

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
}
