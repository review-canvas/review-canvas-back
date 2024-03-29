package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;
import com.romanticpipe.reviewcanvas.service.ShopAdminCreater;
import com.romanticpipe.reviewcanvas.service.ShopAdminValidator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ShopAdminUseCaseImpl implements ShopAdminUseCase {

	private final ShopAdminValidator shopAdminValidator;
	private final ShopAdminCreater shopAdminCreater;

	@Override
	@Transactional(readOnly = true)
	public LoginResponse login(String email, String password) {
		return LoginResponse.from(shopAdminValidator.login(email, password));

	}

	@Override
	@Transactional
	public void signUp(SignUpRequest signUpRequest) {
		shopAdminCreater.signUp(signUpRequest.email(), signUpRequest.password());
	}
}
