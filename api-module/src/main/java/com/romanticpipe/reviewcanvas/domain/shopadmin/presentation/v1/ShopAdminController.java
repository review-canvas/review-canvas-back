package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.SecurityErrorCode;
import com.romanticpipe.reviewcanvas.domain.AdminInterface;
import com.romanticpipe.reviewcanvas.domain.Role;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.ShopAdminUseCase;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.LoginRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.CheckLoginResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.GetGeneralReviewThemeListResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.GetReviewVisibilityTitleResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class ShopAdminController implements ShopAdminApi {

	private final ShopAdminUseCase shopAdminUseCase;

	@Override
	@PostMapping("/shop-admin/login")
	public ResponseEntity<SuccessResponse<LoginResponse>> login(
		@RequestBody LoginRequest loginRequest
	) {
		return SuccessResponse.of(
			shopAdminUseCase.login(loginRequest.email(), loginRequest.password(), Role.SHOP_ADMIN_ROLE)
		).asHttp(HttpStatus.OK);
	}

	@Override
	@PostMapping("/super-admin/login")
	public ResponseEntity<SuccessResponse<LoginResponse>> loginForSuper(
		@RequestBody LoginRequest loginRequest
	) {
		return SuccessResponse.of(
			shopAdminUseCase.login(loginRequest.email(), loginRequest.password(), Role.SUPER_ADMIN_ROLE)
		).asHttp(HttpStatus.OK);
	}

	@PostMapping(value = "/shop-admin/sign-up",
		consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<SuccessResponse<Void>> signUp(
		@Valid @RequestPart SignUpRequest signUpRequest,
		@RequestParam MultipartFile logoImage) {
		shopAdminUseCase.signUp(signUpRequest, logoImage);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@DeleteMapping("/shop-admin/logout")
	@Override
	public ResponseEntity<SuccessResponse<Void>> logout(AdminInterface admin) {
		shopAdminUseCase.logout(admin);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/shop-admin/auth")
	public ResponseEntity<SuccessResponse<CheckLoginResponse>> checkLoginSession(AdminInterface admin) {
		return SuccessResponse.of(shopAdminUseCase.checkLoginSession(admin)).asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/admin/auth")
	public ResponseEntity<SuccessResponse<LoginResponse>> reissuedAccessToken(
		@RequestHeader(AUTHORIZATION) String accessToken) {
		return SuccessResponse.of(shopAdminUseCase.reissuedAccessToken(findToken(accessToken))).asHttp(HttpStatus.OK);
	}

	private String findToken(String bearerToken) {
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring("Bearer ".length());
		}
		throw new BusinessException(SecurityErrorCode.NON_BEARER);
	}

	@Override
	@GetMapping("/shop-admin/review-visibility/titles")
	public ResponseEntity<SuccessResponse<GetReviewVisibilityTitleResponse>> getReviewVisibilityTitle() {
		return SuccessResponse.of(
			shopAdminUseCase.getReviewVisibilityTitle()
		).asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping(value = "/shop-admin/email-check")
	public ResponseEntity<SuccessResponse<Map<String, Boolean>>> emailCheck(
		@RequestParam(value = "email", required = true) String email
	) {
		boolean result = shopAdminUseCase.emailCheck(email);
		return SuccessResponse.of(
			Map.of("duplicate", result)
		).asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/shopadmin/review-design/theme-list")
	public ResponseEntity<SuccessResponse<List<GetGeneralReviewThemeListResponse>>> getGeneralReviewThemeList() {
		return SuccessResponse.of(
			shopAdminUseCase.getGeneralReviewThemeList().stream().map(
				GetGeneralReviewThemeListResponse::from
			).collect(Collectors.toList())
		).asHttp(HttpStatus.OK);
	}
}
