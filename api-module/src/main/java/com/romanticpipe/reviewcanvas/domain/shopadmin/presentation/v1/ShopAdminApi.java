package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignPosition;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.UpdateReviewDesignRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetApplicableReviewDesignResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetGeneralReviewThemeListResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetReviewVisibilityTitleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Tag(name = "ShopAdmin", description = "샵 어드민 API")
interface ShopAdminApi {

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

	@Operation(summary = "리뷰 노출 항목 title 조회 API", description = "리뷰 노출 항목 title을 조회한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
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

	@Operation(summary = "기본 리뷰 테마 리스트 조회 API", description = "기본 Review list/modal 테마 리스트를 조회한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 기본 리뷰 테마 리스트 조회가 완료되었습니다.")
	})
	@GetMapping("/shop-admin/review-design/theme-list")
	ResponseEntity<SuccessResponse<List<GetGeneralReviewThemeListResponse>>> getGeneralReviewThemeList();

	@Operation(summary = "리뷰 디자인 수정 API", description = "리뷰 디자인을 수정한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 디자인 수정이 완료되었습니다.")
	})
	@PatchMapping("/shop-admin/review-design/{reviewDesignId}")
	ResponseEntity<SuccessResponse<Void>> updateReviewDesign(
		@AuthInfo JwtInfo jwtInfo, @PathVariable("reviewDesignId") Integer reviewDesignId,
		@Valid @RequestBody UpdateReviewDesignRequest updateReviewDesignRequest
	);

	@Operation(summary = "적용 가능한 리뷰 디자인 조회 API", description = "적용 가능한 리뷰 디자인을 조회한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 적용 가능한 리뷰 디자인 조회가 완료되었습니다.")
	})
	@GetMapping("/shop-admin/review-design/{shopAdminId}/{reviewDesignPosition}")
	ResponseEntity<SuccessResponse<List<GetApplicableReviewDesignResponse>>> getApplicableReviewDesign(
		@PathVariable("shopAdminId") Integer shopAdminId,
		@PathVariable("reviewDesignPosition") ReviewDesignPosition reviewDesignPosition
	);
}
