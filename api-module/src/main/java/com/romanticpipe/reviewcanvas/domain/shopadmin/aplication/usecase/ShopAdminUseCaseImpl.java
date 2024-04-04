package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.domain.ReviewVisibility;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;
import com.romanticpipe.reviewcanvas.service.ShopAdminCreator;
import com.romanticpipe.reviewcanvas.service.ShopAdminRemover;
import com.romanticpipe.reviewcanvas.service.ShopAdminValidator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ShopAdminUseCaseImpl implements ShopAdminUseCase {

	private final ShopAdminValidator shopAdminValidator;
	private final ShopAdminCreator shopAdminCreator;
	private final ShopAdminRemover shopAdminRemover;

	@Override
	@Transactional(readOnly = true)
	public LoginResponse login(String email, String password) {
		return LoginResponse.from(shopAdminValidator.login(email, password));

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
	@Transactional
	public void quit(Long id) {
		shopAdminRemover.quit(id);
	}
}
