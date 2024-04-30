package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.LayoutRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface ReviewLayoutApi {

	@Operation(summary = "Layout 디자인 저장 API", description = "Layout 디자인 속성을 저장한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 Layout 디자인 저장이 완료되었습니다.")
	})
	@PostMapping(value = "/shop-admin/review-design/layout")
	ResponseEntity<SuccessResponse<Void>> saveLayout(
		@AuthInfo JwtInfo jwtInfo,
		@RequestBody LayoutRequest layoutRequest
	);
}
