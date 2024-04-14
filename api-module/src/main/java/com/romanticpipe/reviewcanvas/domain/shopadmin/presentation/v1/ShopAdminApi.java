package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "ShopAdmin", description = "샵 어드민 API")
interface ShopAdminApi {

	@Operation(summary = "Shop Admin 로그인 API", description = "특정 Shop Admin 계정에 로그인한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 로그인이 완료되었습니다.")
	})
	@PostMapping("/shop-admin/login")
	ResponseEntity<SuccessResponse<LoginResponse>> login(
		@RequestBody LoginRequest loginRequest
	);

	@Operation(summary = "Super Admin 로그인 API", description = "특정 Super Admin 계정에 로그인한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 로그인이 완료되었습니다.")
	})
	@PostMapping("/super-admin/login")
	ResponseEntity<SuccessResponse<LoginResponse>> loginForSuper(
		@RequestBody LoginRequest loginRequest
	);

	@Operation(summary = "회원가입 API", description = "Shop Admin 계정으로 회원가입한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 회원가입이 완료되었습니다.")
	})
	@PostMapping(value = "/shop-admin/sign-up",
		consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
	ResponseEntity<SuccessResponse<Void>> signUp(
		@Valid @RequestPart SignUpRequest signUpRequest,
		@RequestParam MultipartFile logoImage
	);

	@Operation(summary = "로그아웃 API", description = "refresh token을 삭제함으로 로그아웃한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 로그아웃 완료되었습니다.")
	})
	@DeleteMapping("/shop-admin/logout")
	ResponseEntity<SuccessResponse<Void>> logout(AdminInterface admin);

	@Operation(summary = "로그인 상태 확인용 API", description = "현재 로그인 상태를 확인한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 로그인 완료되었습니다.")
	})
	@GetMapping("/shop-admin/auth")
	ResponseEntity<SuccessResponse<CheckLoginResponse>> checkLoginForAdmin(AdminInterface admin);

	@Operation(summary = "회원탈퇴 API", description = "Shop Admin 계정으로 회원탈퇴한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 회원탈퇴가 완료되었습니다.")
	})
	@PostMapping("/shopadmin/quit")
	ResponseEntity<SuccessResponse<Void>> quit(
		@RequestParam(value = "id", required = true) Long id
	);
}
