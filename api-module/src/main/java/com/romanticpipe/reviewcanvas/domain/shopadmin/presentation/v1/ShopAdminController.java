package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.ShopAdminUseCase;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class ShopAdminController implements ShopAdminApi {

	private final ShopAdminUseCase shopAdminUseCase;

	@Override
	@GetMapping("/shopadmin/login")
	public ResponseEntity<SuccessResponse<LoginResponse>> login(
		@RequestParam(value = "email", required = true) String email,
		@RequestParam(value = "password", required = true) String password
	) {
		return SuccessResponse.of(
			shopAdminUseCase.login(email, password)
		).asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/shopadmin/signup")
	public ResponseEntity<SuccessResponse<Boolean>> signUp(
		@RequestBody SignUpRequest signUpRequest) {
		shopAdminUseCase.signUp(signUpRequest);
		return SuccessResponse.of(true).asHttp(HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/shopadmin/quit")
	public ResponseEntity<SuccessResponse<Boolean>> quit(
		@RequestParam(value = "id", required = true) Long id) {
		shopAdminUseCase.quit(id);
		return SuccessResponse.of(true).asHttp(HttpStatus.OK);
	}
}
