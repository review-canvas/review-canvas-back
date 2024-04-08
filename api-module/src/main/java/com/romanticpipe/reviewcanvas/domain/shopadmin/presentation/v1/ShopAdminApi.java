package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.LoginRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.CheckLoginResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ShopAdmin", description = "샵 어드민 API")
interface ShopAdminApi {

	@Operation(summary = "Shop Admin 로그인 API", description = "특정 Shop Admin 계정에 로그인한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 로그인이 완료되었습니다.")
	})
	@PostMapping("/shopadmin/login")
	ResponseEntity<SuccessResponse<LoginResponse>> login(
		@RequestBody LoginRequest loginRequest
	);

	@Operation(summary = "Super Admin 로그인 API", description = "특정 Super Admin 계정에 로그인한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 로그인이 완료되었습니다.")
	})
	@PostMapping("/superAdmin/login")
	ResponseEntity<SuccessResponse<LoginResponse>> loginForSuper(
		@RequestBody LoginRequest loginRequest
	);

	@Operation(summary = "회원가입 API", description = "Shop Admin 계정으로 회원가입한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 회원가입이 완료되었습니다.")
	})

	@PostMapping("/shopadmin/signup")
	ResponseEntity<SuccessResponse<Void>> signUp(
		@RequestBody SignUpRequest signUpRequest
	);

	@Operation(summary = "로그인 상태 확인용 API", description = "현재 로그인 상태를 확인한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 로그인 완료되었습니다.")
	})
	@GetMapping("/shopadmin/auth/shop")
	ResponseEntity<SuccessResponse<CheckLoginResponse>> checkLoginForAdmin();
}
