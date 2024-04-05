package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.common.security.SecurityUtils;
import com.romanticpipe.reviewcanvas.common.security.TokenProvider;
import com.romanticpipe.reviewcanvas.domain.ReviewVisibility;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.TokenInfoResponse;
import com.romanticpipe.reviewcanvas.service.AdminAuthCreater;
import com.romanticpipe.reviewcanvas.service.ShopAdminCreator;
import com.romanticpipe.reviewcanvas.service.ShopAdminValidator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ShopAdminUseCaseImpl implements ShopAdminUseCase {

	private final ShopAdminValidator shopAdminValidator;
	private final AdminAuthCreater adminAuthCreater;
	private final TokenProvider tokenProvider;
	private final ShopAdminCreator shopAdminCreator;

	@Override
	@Transactional
	public LoginResponse login(String email, String password) {
		ShopAdmin shopAdmin = shopAdminValidator.login(email, password);
		TokenInfoResponse tokenInfoResponse = tokenProvider.createToken(shopAdmin);
		adminAuthCreater.save(tokenInfoResponse.getRefreshToken(), shopAdmin);
		return LoginResponse.from(shopAdmin, tokenInfoResponse);

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
