package com.romanticpipe.reviewcanvas.domain.auth.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.AdminRole;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.AuthUseCase;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.request.LoginRequest;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.request.ReissueAccessTokenRequest;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response.LoginResponse;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response.ReissueAccessTokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<SuccessResponse<LoginResponse>> loginForShopAdmin(
		@Valid @RequestBody LoginRequest loginRequest
	) {
		return SuccessResponse.of(
			authUseCase.login(loginRequest.email(), loginRequest.password(), AdminRole.ROLE_SHOP_ADMIN)
		).asHttp(HttpStatus.OK);
	}

	@Override
	@PostMapping("/super-admin/login")
	public ResponseEntity<SuccessResponse<LoginResponse>> loginForSuperAdmin(
		@Valid @RequestBody LoginRequest loginRequest
	) {
		return SuccessResponse.of(
			authUseCase.login(loginRequest.email(), loginRequest.password(), AdminRole.ROLE_SUPER_ADMIN)
		).asHttp(HttpStatus.OK);
	}

	@Override
	@PostMapping("/logout")
	public ResponseEntity<SuccessResponse<Void>> logout(@AuthInfo JwtInfo jwtInfo) {
		authUseCase.logout(jwtInfo.adminId(), jwtInfo.adminRole());
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@PostMapping("/reissue-access-token")
	public ResponseEntity<SuccessResponse<ReissueAccessTokenResponse>> reissueAccessToken(
		@Valid @RequestBody ReissueAccessTokenRequest request) {
		return SuccessResponse.of(
			authUseCase.reissuedAccessToken(request.refreshToken())
		).asHttp(HttpStatus.OK);
	}
}