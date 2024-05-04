package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import java.util.HashMap;
import java.util.Map;

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
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewColumnService;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewContainerService;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewLayoutService;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewTitleService;
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
	private final ReviewLayoutService reviewLayoutService;
	private final ReviewContainerService reviewContainerService;
	private final ReviewColumnService reviewColumnService;
	private final ReviewTitleService reviewTitleService;
	private final TermsService termsService;
	private final TermsConsentService termsConsentService;

	@Override
	@Transactional
	public void signUp(SignUpRequest signUpRequest) {
		shopAdminValidator.validateEmailDuplicated(signUpRequest.email());
		Map<Integer, Boolean> termsTags = new HashMap<>();
		for (String tag : signUpRequest.consentedTermsTags()) {
			Terms terms = termsService.validateByTag(tag);
			termsTags.put(terms.getId(), Boolean.TRUE);
		}
		for (String tag : signUpRequest.refusedTermsTags()) {
			Terms terms = termsService.validateByTag(tag);
			termsTags.put(terms.getId(), Boolean.FALSE);
		}
		termsService.validateMandatoryTerms(signUpRequest.consentedTermsTags());

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
		ReviewTitle reviewTitle = ReviewTitle.createTitle(shopAdmin.getId());
		ReviewTitle reviewTitleDescription = ReviewTitle.createDescription(shopAdmin.getId());
		AdminAuth adminAuth = AdminAuth.createShopAdminAuth(shopAdmin.getId());

		reviewLayoutService.save(reviewLayout);
		reviewContainerService.save(reviewContainer);
		reviewColumnService.save(reviewColumn);
		reviewTitleService.save(reviewTitle);
		reviewTitleService.save(reviewTitleDescription);
		adminAuthCreater.save(adminAuth);

		termsTags.forEach((termsId, isConsent) -> {
			TermsConsent termsConsent = TermsConsent.builder()
				.termsId(termsId)
				.consent(isConsent)
				.shopAdminId(shopAdmin.getId())
				.build();
			termsConsentService.save(termsConsent);
		});
	}

	@Override
	@Transactional(readOnly = true)
	public boolean emailCheck(String email) {
		return shopAdminValidator.isExistEmail(email);
	}

}
