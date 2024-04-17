package com.romanticpipe.reviewcanvas.domain.auth.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.request.LoginRequest;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.request.ReissueAccessTokenRequest;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response.LoginResponse;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response.ReissueAccessTokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "ShopAdmin", description = "샵 어드민 API")
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

	@Operation(summary = "로그아웃 API", description = "refresh token을 삭제함으로 로그아웃한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 로그아웃 완료되었습니다.")
	})
	@PostMapping("/logout")
	ResponseEntity<SuccessResponse<Void>> logout(@AuthInfo JwtInfo jwtInfo);

	@Operation(summary = "AccessToken 재발급 API", description = "RefreshToken에 기반해 Access 토큰을 재발급한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 재발급 완료되었습니다.")
	})
	@PostMapping("/reissue-access-token")
	ResponseEntity<SuccessResponse<ReissueAccessTokenResponse>> reissueAccessToken(
		@Valid @RequestBody ReissueAccessTokenRequest request);
}
