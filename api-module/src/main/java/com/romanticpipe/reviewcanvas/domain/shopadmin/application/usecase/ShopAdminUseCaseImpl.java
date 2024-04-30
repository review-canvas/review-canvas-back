package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.admin.domain.AdminAuth;
import com.romanticpipe.reviewcanvas.admin.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.admin.service.AdminAuthCreater;
import com.romanticpipe.reviewcanvas.admin.service.ShopAdminCreator;
import com.romanticpipe.reviewcanvas.admin.service.ShopAdminValidator;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewContainer;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewTitle;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Terms;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.TermsConsent;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewColumnCreator;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewContainerCreator;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewLayoutCreator;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewTitleCreator;
import com.romanticpipe.reviewcanvas.reviewproperty.service.TermsConsentService;
import com.romanticpipe.reviewcanvas.reviewproperty.service.TermsService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ShopAdminUseCaseImpl implements ShopAdminUseCase {

	private final PasswordEncoder passwordEncoder;
	private final AdminAuthCreater adminAuthCreater;
	private final ShopAdminCreator shopAdminCreator;
	private final ShopAdminValidator shopAdminValidator;
	private final ReviewLayoutCreator reviewLayoutCreator;
	private final ReviewContainerCreator reviewContainerCreator;
	private final ReviewColumnCreator reviewColumnCreator;
	private final ReviewTitleCreator reviewTitleCreator;
	private final TermsService termsService;
	private final TermsConsentService termsConsentService;

	@Override
	@Transactional
	public void signUp(SignUpRequest signUpRequest) {
		shopAdminValidator.validateEmailDuplicated(signUpRequest.email());

		ShopAdmin shopAdmin = ShopAdmin.builder()
			.email(signUpRequest.email())
			.password(passwordEncoder.encode(signUpRequest.password()))
			.mallName(signUpRequest.mallName())
			.mallNumber(signUpRequest.phoneNumber())
			.phoneNumber(signUpRequest.phoneNumber())
			.mallId(signUpRequest.mallId())
			.approveStatus(false)
			.build();
		shopAdminCreator.save(shopAdmin);

		ReviewLayout reviewLayout = ReviewLayout.create(shopAdmin.getId());
		ReviewContainer reviewContainer = ReviewContainer.create(shopAdmin.getId());
		ReviewColumn reviewColumn = ReviewColumn.create(shopAdmin.getId());
		ReviewTitle reviewTitle = ReviewTitle.create(shopAdmin.getId());
		AdminAuth adminAuth = AdminAuth.createShopAdminAuth(shopAdmin.getId());

		reviewLayoutCreator.save(reviewLayout);
		reviewContainerCreator.save(reviewContainer);
		reviewColumnCreator.save(reviewColumn);
		reviewTitleCreator.save(reviewTitle);
		adminAuthCreater.save(adminAuth);

		for (String tag : signUpRequest.consentedTermsTags()) {
			Terms terms = termsService.findByTag(tag);
			TermsConsent termsConsent = TermsConsent.builder()
				.termsId(terms.getId())
				.consent(true)
				.shopAdminId(shopAdmin.getId())
				.build();
			termsConsentService.save(termsConsent);
		}
		for (String tag : signUpRequest.refusedTermsTags()) {
			Terms terms = termsService.findByTag(tag);
			TermsConsent termsConsent = TermsConsent.builder()
				.termsId(terms.getId())
				.consent(false)
				.shopAdminId(shopAdmin.getId())
				.build();
			termsConsentService.save(termsConsent);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public boolean emailCheck(String email) {
		return shopAdminValidator.isExistEmail(email);
	}

}
