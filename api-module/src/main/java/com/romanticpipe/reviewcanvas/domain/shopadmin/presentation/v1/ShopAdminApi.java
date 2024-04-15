package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.AdminInterface;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.LoginRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.CheckLoginResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.GetGeneralReviewThemeListResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.GetReviewVisibilityTitleResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

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
	ResponseEntity<SuccessResponse<CheckLoginResponse>> checkLoginSession(AdminInterface admin);

	@Operation(summary = "AccessToken 재발급 API", description = "RefreshToken에 기반해 Access 토큰을 재발급한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 재발급 완료되었습니다.")
	})
	@GetMapping("/admin/auth")
	ResponseEntity<SuccessResponse<LoginResponse>> reissuedAccessToken(
		@RequestHeader(AUTHORIZATION) String accessToken);

	@Operation(summary = "리뷰 노출 항목 title 조회 API", description = "리뷰 노출 항목 title을 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 노출 항목 title 조회가 완료되었습니다.")
	})
	@GetMapping(value = "/shop-admin/review-visibility/titles")
	ResponseEntity<SuccessResponse<GetReviewVisibilityTitleResponse>> getReviewVisibilityTitle();

	@Operation(summary = "이메일 중복 체크 API", description = "특정 이메일의 중복을 체크한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 이메일 중복 체크가 완료되었습니다.",
			content = @Content(
				schemaProperties = {
					@SchemaProperty(name = "success", schema = @Schema(type = "boolean", description = "성공 여부")),
					@SchemaProperty(name = "duplicate", schema = @Schema(type = "boolean", description = "중복 여부"))
				}
			))
	})
	@GetMapping("/shop-admin/email-check")
	ResponseEntity<SuccessResponse<Map<String, Boolean>>> emailCheck(
		@RequestParam(value = "email", required = true) String email
	);

	@Operation(summary = "기본 리뷰 테마 리스트 조회 API", description = "기본 Review list/modal 테마 리스트를 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 기본 리뷰 테마 리스트 조회가 완료되었습니다.")
	})
	@GetMapping("/shop-admin/review-design/theme-list")
	ResponseEntity<SuccessResponse<List<GetGeneralReviewThemeListResponse>>> getGeneralReviewThemeList();
}
