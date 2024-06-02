package com.romanticpipe.reviewcanvas.domain.auth.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.CustomCookieName;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.AuthUseCase;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.request.LoginRequest;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response.ShopAdminLoginResponse;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response.SuperAdminLoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class AuthController implements AuthApi {

	private final AuthUseCase authUseCase;

	@Override
	@PostMapping("/shop-admin/login")
	public ResponseEntity<SuccessResponse<ShopAdminLoginResponse>> loginForShopAdmin(
		@Valid @RequestBody LoginRequest loginRequest
	) {
		var shopAdminLoginResponse = authUseCase.shopAdminLogin(loginRequest.email(), loginRequest.password());
		var refreshTokenResponseCookie = getRefreshTokenResponseCookie(shopAdminLoginResponse.refreshToken());
		return SuccessResponse.of(shopAdminLoginResponse).okWithCookie(refreshTokenResponseCookie);
	}

	@Override
	@PostMapping("/super-admin/login")
	public ResponseEntity<SuccessResponse<SuperAdminLoginResponse>> loginForSuperAdmin(
		@Valid @RequestBody LoginRequest loginRequest
	) {
		var superAdminLoginResponse = authUseCase.superAdminLogin(loginRequest.email(), loginRequest.password());
		var refreshTokenResponseCookie = getRefreshTokenResponseCookie(superAdminLoginResponse.refreshToken());
		return SuccessResponse.of(superAdminLoginResponse).okWithCookie(refreshTokenResponseCookie);
	}

	@Override
	@PostMapping("/logout")
	public ResponseEntity<SuccessResponse<Void>> logout(@AuthInfo JwtInfo jwtInfo) {
		authUseCase.logout(jwtInfo.adminId(), jwtInfo.adminRole());
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/auth/check")
	public ResponseEntity<SuccessResponse<Void>> authCheck() {
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	private ResponseCookie getRefreshTokenResponseCookie(String refreshToken) {
		return ResponseCookie.from(CustomCookieName.REFRESH_TOKEN, refreshToken)
			.httpOnly(true)
			.secure(true)
			.path("/")
			.build();
	}
}
