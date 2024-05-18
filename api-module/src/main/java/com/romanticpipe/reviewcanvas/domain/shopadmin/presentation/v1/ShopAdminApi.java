package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.UpdateShopAdminInfoRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetShopAdminInfoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "ShopAdmin", description = "샵 어드민 API")
interface ShopAdminApi {

	@Operation(summary = "회원가입 API", description = "Shop Admin 계정으로 회원가입한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 회원가입이 완료되었습니다.")
	})
	@PostMapping(value = "/shop-admin/sign-up")
	ResponseEntity<SuccessResponse<Void>> signUp(
		@Valid @RequestBody SignUpRequest signUpRequest
	);

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

	@Operation(summary = "내 정보 조회 API", description = "마이페이지에서 내 정보를 조회할 때 사용될 api")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 내 정보 조회를 완료했습니다.")
	})
	@GetMapping("/shop-admin")
	ResponseEntity<SuccessResponse<GetShopAdminInfoResponse>> getShopAdminInfo(@AuthInfo JwtInfo jwtInfo);

	@Operation(summary = "내 정보 수정 API", description = "마이페이지에서 내 정보를 수정할 때 사용될 api")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 내 정보 조회를 수정했습니다.")
	})
	@PatchMapping("/shop-admin")
	ResponseEntity<SuccessResponse<Void>> updateShopAdminInfo(
		@RequestBody UpdateShopAdminInfoRequest request,
		@AuthInfo JwtInfo jwtInfo
	);

	@Operation(summary = "회원탈퇴 API", description = "Shop Admin 계정으로 회원탈퇴한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 회원탈퇴가 완료되었습니다.")
	})
	@DeleteMapping("/shop-admin/quit")
	ResponseEntity<SuccessResponse<Void>> quit(
		@AuthInfo JwtInfo jwtInfo
	);
}
