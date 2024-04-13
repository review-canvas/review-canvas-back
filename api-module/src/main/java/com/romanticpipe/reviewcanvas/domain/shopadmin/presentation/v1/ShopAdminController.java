package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.ShopAdminUseCase;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.GetGeneralReviewThemeListResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;

import jakarta.validation.Valid;
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
	@PostMapping(value = "/shopadmin/signup",
		consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<SuccessResponse<Void>> signUp(
		@Valid @RequestPart SignUpRequest signUpRequest,
		@RequestParam MultipartFile logoImage) {
		shopAdminUseCase.signUp(signUpRequest, logoImage);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/shopadmin/review-design/theme-list")
	ResponseEntity<SuccessResponse<List<GetGeneralReviewThemeListResponse>>> getGeneralReviewThemeList() {
		return SuccessResponse.of().asHttp(HttpStatus.OK);
	}
}
