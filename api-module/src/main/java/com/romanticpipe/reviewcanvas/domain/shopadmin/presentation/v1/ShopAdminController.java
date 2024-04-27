package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignPosition;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.ShopAdminUseCase;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.UpdateReviewDesignRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetApplicableReviewDesignResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetGeneralReviewThemeListResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetReviewVisibilityTitleResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class ShopAdminController implements ShopAdminApi {

	private final ShopAdminUseCase shopAdminUseCase;

	@PostMapping(value = "/shop-admin/sign-up", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
		MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<SuccessResponse<Void>> signUp(@Valid @RequestPart SignUpRequest signUpRequest,
		@RequestParam MultipartFile logoImage) {
		shopAdminUseCase.signUp(signUpRequest, logoImage);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/shop-admin/review-visibility/titles")
	public ResponseEntity<SuccessResponse<GetReviewVisibilityTitleResponse>> getReviewVisibilityTitle() {
		return SuccessResponse.of(shopAdminUseCase.getReviewVisibilityTitle()).asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping(value = "/shop-admin/email-check")
	public ResponseEntity<SuccessResponse<Map<String, Boolean>>> emailCheck(
		@RequestParam(value = "email", required = true) String email) {
		boolean result = shopAdminUseCase.emailCheck(email);
		return SuccessResponse.of(Map.of("duplicate", result)).asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/shop-admin/review-design/theme-list")
	public ResponseEntity<SuccessResponse<List<GetGeneralReviewThemeListResponse>>> getGeneralReviewThemeList() {
		return SuccessResponse.of(shopAdminUseCase.getGeneralReviewThemeList()
			.stream()
			.map(GetGeneralReviewThemeListResponse::from)
			.collect(Collectors.toList())).asHttp(HttpStatus.OK);
	}

	@Override
	@PatchMapping("/shop-admin/review-design/{reviewDesignId}")
	public ResponseEntity<SuccessResponse<Void>> updateReviewDesign(@AuthInfo JwtInfo jwtInfo,
		@PathVariable Integer reviewDesignId, @Valid @RequestBody UpdateReviewDesignRequest updateReviewDesignRequest) {
		shopAdminUseCase.updateReviewDesign(jwtInfo.adminId(), reviewDesignId, updateReviewDesignRequest);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/shop-admin/review-design/{shopAdminId}/{reviewDesignPosition}")
	public ResponseEntity<SuccessResponse<List<GetApplicableReviewDesignResponse>>> getApplicableReviewDesign(
		@PathVariable("shopAdminId") Integer shopAdminId, @PathVariable("reviewDesignPosition") String position) {
		ReviewDesignPosition reviewDesignPosition =
			position.equals("review_list") ? ReviewDesignPosition.REVIEW_LIST : ReviewDesignPosition.REVIEW_MODAL;
		List<ReviewDesign> reviewDesigns = shopAdminUseCase.getCustomReviewThemeList(shopAdminId);
		List<GetApplicableReviewDesignResponse> response = new ArrayList<>();
		for (ReviewDesign reviewDesign : reviewDesigns) {
			if (reviewDesign.getReviewDesignPosition() == reviewDesignPosition) {
				response.add(GetApplicableReviewDesignResponse.from(reviewDesign));
			}
		}
		return SuccessResponse.of(response).asHttp(HttpStatus.OK);
	}

}
