package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.ShopAdminUseCase;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@PostMapping("/shopadmin/signup")
	public ResponseEntity<SuccessResponse<Void>> signUp(
		@Valid @RequestBody SignUpRequest signUpRequest) {
		shopAdminUseCase.signUp(signUpRequest);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}
}
