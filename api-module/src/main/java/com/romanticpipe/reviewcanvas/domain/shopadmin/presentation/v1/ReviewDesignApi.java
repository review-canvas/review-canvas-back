package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.UpdateReviewDesignRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "ReviewDesign", description = "리뷰 디자인 API")
public interface ReviewDesignApi {

	@Operation(summary = "리뷰 디자인 수정 API", description = "리뷰 디자인을 수정한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 디자인 수정이 완료되었습니다.")
	})
	@PatchMapping("/shop-admin/review-design/{reviewDesignId}")
	ResponseEntity<SuccessResponse<Void>> updateReviewDesign(
		@AuthInfo JwtInfo jwtInfo, @PathVariable("reviewDesignId") Integer reviewDesignId,
		@Valid @RequestBody UpdateReviewDesignRequest updateReviewDesignRequest
	);
}
