package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.common.security.SecurityUtils;
import com.romanticpipe.reviewcanvas.common.security.TokenProvider;
import com.romanticpipe.reviewcanvas.domain.AdminAuth;
import com.romanticpipe.reviewcanvas.domain.ReviewVisibility;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;
import com.romanticpipe.reviewcanvas.service.AdminAuthCreater;
import com.romanticpipe.reviewcanvas.service.AdminAuthValidator;
import com.romanticpipe.reviewcanvas.service.ShopAdminCreator;
import com.romanticpipe.reviewcanvas.service.ShopAdminValidator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ShopAdminUseCaseImpl implements ShopAdminUseCase {

	private final TokenProvider tokenProvider;
	private final AdminAuthCreater adminAuthCreater;
	private final AdminAuthValidator adminAuthValidator;
	private final ShopAdminCreator shopAdminCreator;
	private final ShopAdminValidator shopAdminValidator;

	@Override
	@Transactional
	public LoginResponse login(String email, String password) {
		ShopAdmin shopAdmin = shopAdminValidator.login(email, password);
		AdminAuth adminAuth = adminAuthValidator.findAdminAuthById(shopAdmin.getId());

		String accessToken = tokenProvider.createToken(shopAdmin);
		String refreshToken = adminAuth.getRefreshToken();

		if (refreshToken.isEmpty() || tokenProvider.isExpiredToken(refreshToken)) {
			tokenProvider.createRefreshToken(shopAdmin.getEmail(), adminAuth);
		}

		if (adminAuth.getId() == null) {
			adminAuthCreater.save(adminAuth);
		}

		return LoginResponse.from(shopAdmin, accessToken);

	}

	@Override
	@Transactional
	public void signUp(SignUpRequest signUpRequest) {
		shopAdminValidator.isExistTheme(signUpRequest.reviewDesignId());

		ReviewVisibility reviewVisibility = ReviewVisibility.builder()
			.title(signUpRequest.title())
			.author(signUpRequest.author())
			.point(signUpRequest.point())
			.media(signUpRequest.media())
			.content(signUpRequest.content())
			.createdAt(signUpRequest.createdAt())
			.updatedAt(signUpRequest.updatedAt())
			.build();
		ShopAdmin shopAdmin = ShopAdmin.builder()
			.reviewVisibility(reviewVisibility)
			.email(signUpRequest.email())
			.password(signUpRequest.password())
			.name(signUpRequest.name())
			.logoImageUrl(signUpRequest.logoImageUrl())
			.mallNumber(signUpRequest.mallNumber())
			.phoneNumber(signUpRequest.phoneNumber())
			.approveStatus(false)
			.selectedReviewDesignId(signUpRequest.reviewDesignId())
			.build();

		shopAdminCreator.signUp(shopAdmin);
	}

	@Override
	@Transactional(readOnly = true)
	public Long tokenCheckByShopAdmin() {
		return shopAdminValidator.isExsitUser(SecurityUtils.getLoggedInAdminEmail()).getId();
	}
}
