package com.romanticpipe.reviewcanvas.domain.auth.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.request.LoginRequest;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Auth", description = "인증 API")
interface AuthApi {

	@Operation(summary = "Shop Admin 로그인 API", description = "특정 Shop Admin 계정에 로그인한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 로그인이 완료되었습니다.")
	})
	@PostMapping("/shop-admin/login")
	ResponseEntity<SuccessResponse<LoginResponse>> loginForShopAdmin(
		@Valid @RequestBody LoginRequest loginRequest
	);

	@Operation(summary = "Super Admin 로그인 API", description = "특정 Super Admin 계정에 로그인한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 로그인이 완료되었습니다.")
	})
	@PostMapping("/super-admin/login")
	ResponseEntity<SuccessResponse<LoginResponse>> loginForSuperAdmin(
		@Valid @RequestBody LoginRequest loginRequest
	);

	@Operation(summary = "로그아웃 API", description = "refresh token을 삭제함으로 로그아웃한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 로그아웃 완료되었습니다.")
	})
	@PostMapping("/logout")
	ResponseEntity<SuccessResponse<Void>> logout(@AuthInfo JwtInfo jwtInfo);

	@Operation(summary = "로그인 여부 확인 API", description = "access token 및 refresh token으로 로그인 여부를 확인한다",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "로그인된 상태입니다.")
	})
	@GetMapping("/auth/check")
	ResponseEntity<SuccessResponse<Void>> authCheck();
}
