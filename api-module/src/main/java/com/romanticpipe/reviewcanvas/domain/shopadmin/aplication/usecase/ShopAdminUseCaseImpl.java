package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.domain.DesignItemSuper;
import com.romanticpipe.reviewcanvas.domain.ReviewItem;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
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
		DesignItemSuper designItemSuper = shopAdminValidator.isExistTheme(signUpRequest.theme_name());
		ShopAdmin shopAdmin = shopAdminValidator.isExsitUser(signUpRequest.email());
		ShopAdmin shopAdminBuild = new ShopAdmin(signUpRequest.email(), signUpRequest.password(), signUpRequest.name(),
			signUpRequest.logoImageUrl(), signUpRequest.mall_number(), signUpRequest.phone_number(),
			designItemSuper.getId());
		ReviewItem reviewItemBuild = new ReviewItem(signUpRequest.title(), signUpRequest.author(),
			signUpRequest.point(),
			signUpRequest.media(), signUpRequest.content(), signUpRequest.createdAt(), signUpRequest.updatedAt(),
			shopAdmin.getId());
		shopAdminCreater.signUp(shopAdminBuild, reviewItemBuild);
	}
}
