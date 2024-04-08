package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.Role;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.ShopAdminUseCase;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.LoginRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.CheckLoginResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class ShopAdminController implements ShopAdminApi {

	private final ShopAdminUseCase shopAdminUseCase;

	@Override
	@PostMapping("/shopadmin/login")
	public ResponseEntity<SuccessResponse<LoginResponse>> login(
		@RequestBody LoginRequest loginRequest
	) {
		return SuccessResponse.of(
			shopAdminUseCase.login(loginRequest.email(), loginRequest.password(), Role.USER)
		).asHttp(HttpStatus.OK);
	}

	@Override
	@PostMapping("/superadmin/login")
	public ResponseEntity<SuccessResponse<LoginResponse>> loginForSuper(
		@RequestBody LoginRequest loginRequest
	) {
		return SuccessResponse.of(
			shopAdminUseCase.login(loginRequest.email(), loginRequest.password(), Role.SUPER)
		).asHttp(HttpStatus.OK);
	}

	@Override
	@PostMapping("/shopadmin/signup")
	public ResponseEntity<SuccessResponse<Void>> signUp(
		@RequestBody SignUpRequest signUpRequest) {
		shopAdminUseCase.signUp(signUpRequest);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/shopadmin/auth")
	public ResponseEntity<SuccessResponse<CheckLoginResponse>> checkLoginForAdmin() {
		return SuccessResponse.of(shopAdminUseCase.checkLoginForAdmin()).asHttp(HttpStatus.OK);
	}
}
