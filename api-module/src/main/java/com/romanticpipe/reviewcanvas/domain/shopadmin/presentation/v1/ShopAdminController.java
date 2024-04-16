package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.ShopAdminUseCase;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.GetGeneralReviewThemeListResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.GetReviewVisibilityTitleResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class ShopAdminController implements ShopAdminApi {

	private final ShopAdminUseCase shopAdminUseCase;

	@PostMapping(value = "/shop-admin/sign-up",
		consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<SuccessResponse<Void>> signUp(
		@Valid @RequestPart SignUpRequest signUpRequest,
		@RequestParam MultipartFile logoImage) {
		shopAdminUseCase.signUp(signUpRequest, logoImage);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
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
	@GetMapping("/shop-admin/review-design/theme-list")
	public ResponseEntity<SuccessResponse<List<GetGeneralReviewThemeListResponse>>> getGeneralReviewThemeList() {
		return SuccessResponse.of(
			shopAdminUseCase.getGeneralReviewThemeList().stream().map(
				GetGeneralReviewThemeListResponse::from
			).collect(Collectors.toList())
		).asHttp(HttpStatus.OK);
	}
}
