package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.ShopAdminUseCase;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.UpdateShopAdminInfoRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetShopAdminInfoResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetShopInfoResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class ShopAdminController implements ShopAdminApi {

	private final ShopAdminUseCase shopAdminUseCase;

	@PostMapping(value = "/shop-admin/sign-up")
	public ResponseEntity<SuccessResponse<Void>> signUp(
		@RequestBody SignUpRequest signUpRequest) {
		shopAdminUseCase.signUp(signUpRequest);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
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
	@GetMapping("/shop-admin")
	public ResponseEntity<SuccessResponse<GetShopAdminInfoResponse>> getShopAdminInfo(@AuthInfo JwtInfo jwtInfo) {
		return SuccessResponse.of(
			shopAdminUseCase.getShopAdminInfo(jwtInfo.adminId())
		).asHttp(HttpStatus.OK);
	}

	@Override
	@PatchMapping("/shop-admin")
	public ResponseEntity<SuccessResponse<Void>> updateShopAdminInfo(
		@RequestBody UpdateShopAdminInfoRequest request,
		@AuthInfo JwtInfo jwtInfo
	) {
		shopAdminUseCase.updateShopAdminInfo(request, jwtInfo.adminId());
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/shop-admin/quit")
	public ResponseEntity<SuccessResponse<Void>> quit(@AuthInfo JwtInfo jwtInfo) {
		shopAdminUseCase.deleteShopAdmin(jwtInfo.adminId(), jwtInfo.adminRole(), LocalDateTime.now());
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/super-admin/shops")
	public ResponseEntity<SuccessResponse<PageResponse<GetShopInfoResponse>>> getShopInfos(
		@RequestParam(value = "size", required = false, defaultValue = "10") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page
	) {
		return SuccessResponse.of(shopAdminUseCase.getShopInfos(PageableRequest.of(page, size))).asHttp(HttpStatus.OK);
	}
}
