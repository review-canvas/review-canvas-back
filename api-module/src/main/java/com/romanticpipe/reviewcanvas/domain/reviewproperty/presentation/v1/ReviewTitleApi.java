package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateReviewTitleAttributeRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ReviewTitle", description = "리뷰 제목 디자인 API")
public interface ReviewTitleApi {

	@Operation(summary = "리뷰 제목 디자인 수정 API", description = "리뷰 제목의 디자인 속성값을 수정한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 제목 디자인 수정이 완료되었습니다."
		)
	})
	@PatchMapping("/shop-admin/review-title")
	ResponseEntity<SuccessResponse<Void>> updateReviewTitleAttribute(
		@AuthInfo JwtInfo jwtInfo, @RequestBody UpdateReviewTitleAttributeRequest updateReviewTitleAttributeRequest
	);
}
