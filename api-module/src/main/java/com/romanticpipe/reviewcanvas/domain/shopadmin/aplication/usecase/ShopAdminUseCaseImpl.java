package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.common.security.SecurityUtils;
import com.romanticpipe.reviewcanvas.common.security.TokenProvider;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.TokenInfoResponse;
import com.romanticpipe.reviewcanvas.service.AdminAuthCreater;
import com.romanticpipe.reviewcanvas.service.ShopAdminCreater;
import com.romanticpipe.reviewcanvas.service.ShopAdminValidator;
import com.romanticpipe.reviewcanvas.service.SuperAdminValidator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ShopAdminUseCaseImpl implements ShopAdminUseCase {

	private final ShopAdminValidator shopAdminValidator;
	private final SuperAdminValidator superAdminValidator;
	private final ShopAdminCreater shopAdminCreater;
	private final AdminAuthCreater adminAuthCreater;
	private final TokenProvider tokenProvider;

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
		shopAdminCreater.signUp(signUpRequest.email(), signUpRequest.password());
	}

	@Override
	@Transactional(readOnly = true)
	public Long tokenCheckByShopAdmin() {
		return shopAdminValidator.findByEmail(SecurityUtils.getLoggedInAdminEmail()).getId();
	}
}
