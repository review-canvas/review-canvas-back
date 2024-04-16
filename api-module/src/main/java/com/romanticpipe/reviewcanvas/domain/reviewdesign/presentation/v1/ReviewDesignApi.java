package com.romanticpipe.reviewcanvas.domain.reviewdesign.presentation.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.reviewdesign.application.usecase.response.GetReviewDesignResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Review", description = "리뷰 디자인 API")
public interface ReviewDesignApi {

	@Operation(summary = "선택 중인 리뷰 디자인 조회 API", description = "특정 Shop admin이 선택 중인 리뷰 디자인을 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 선택 중인 리뷰 디자인 조회가 완료되었습니다.")
	})
	@GetMapping("/review_designs/{shop_admin_id}/selected_review_design")
	ResponseEntity<SuccessResponse<GetReviewDesignResponse>> getSelectedReviewDesign(
		@PathVariable("shop_admin_id") long shopAdminId,
		@RequestParam String position
	);

}
