package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ShopAdmin", description = "샵 어드민 API")
interface ShopAdminApi {

	@Operation(summary = "로그인 API", description = "특정 Shop Admin 계정에 로그인한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 로그인이 완료되었습니다.")
	})
	@GetMapping("/products/{productId}/reviews")
	ResponseEntity<SuccessResponse<LoginResponse>> login(
		@RequestParam(value = "email", required = true) String email,
		@RequestParam(value = "password", required = true) String password
	);
}
