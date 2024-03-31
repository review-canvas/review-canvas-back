package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.common.security.SecurityUtils;
import com.romanticpipe.reviewcanvas.common.security.TokenProvider;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.TokenInfoResponse;
import com.romanticpipe.reviewcanvas.service.ShopAdminCreater;
import com.romanticpipe.reviewcanvas.service.ShopAdminValidator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ShopAdminUseCaseImpl implements ShopAdminUseCase {

	private final ShopAdminValidator shopAdminValidator;
	private final ShopAdminCreater shopAdminCreater;
	private final TokenProvider tokenProvider;

	@Override
	@Transactional
	public LoginResponse login(String email, String password) {
		ShopAdmin user = shopAdminValidator.login(email, password);

		TokenInfoResponse tokenInfoResponse = tokenProvider.createToken(email, user.getId());
		return LoginResponse.from(shopAdminValidator.login(email, password), tokenInfoResponse);

	}

	@Override
	@Transactional
	public void signUp(SignUpRequest signUpRequest) {
		shopAdminCreater.signUp(signUpRequest.email(), signUpRequest.password());
	}

	@Override
	@Transactional(readOnly = true)
	public Long loginByAccesstoken() {
		return shopAdminValidator.loginByAccesstoken(SecurityUtils.getLoggedInShopAdmin()).getId();
	}
}
