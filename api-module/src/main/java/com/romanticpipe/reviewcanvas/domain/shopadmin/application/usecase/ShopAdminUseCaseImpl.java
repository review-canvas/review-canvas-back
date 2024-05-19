package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.admin.domain.AdminAuth;
import com.romanticpipe.reviewcanvas.admin.domain.AdminRole;
import com.romanticpipe.reviewcanvas.admin.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.admin.service.AdminAuthService;
import com.romanticpipe.reviewcanvas.admin.service.ShopAdminService;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.UpdateShopAdminInfoRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetShopAdminInfoResponse;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewPropertyService;
import com.romanticpipe.reviewcanvas.reviewproperty.service.TermsConsentService;
import com.romanticpipe.reviewcanvas.reviewproperty.service.TermsService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ShopAdminUseCaseImpl implements ShopAdminUseCase {

	private final PasswordEncoder passwordEncoder;
	private final AdminAuthService adminAuthService;
	private final ShopAdminService shopAdminService;
	private final ReviewPropertyService reviewPropertyService;
	private final TermsService termsService;
	private final TermsConsentService termsConsentService;

	@Override
	@Transactional
	public void signUp(SignUpRequest signUpRequest) {
		shopAdminService.validateEmailDuplicated(signUpRequest.email());
		termsService.validateMandatoryTerms(signUpRequest.consentedTermsIds());

		ShopAdmin shopAdmin = ShopAdmin.builder()
			.email(signUpRequest.email())
			.password(passwordEncoder.encode(signUpRequest.password()))
			.mallName(signUpRequest.mallName())
			.mallNumber(signUpRequest.phoneNumber())
			.phoneNumber(signUpRequest.phoneNumber())
			.mallId(signUpRequest.mallId())
			.approveStatus(false)
			.build();
		shopAdminService.save(shopAdmin);

		termsConsentService.createAll(signUpRequest.consentedTermsIds(), shopAdmin.getId());
		reviewPropertyService.createDefaultReviewProperty(shopAdmin.getId());
		adminAuthService.createShopAdminAuth(shopAdmin.getId());
	}

	@Override
	@Transactional(readOnly = true)
	public boolean emailCheck(String email) {
		return shopAdminService.isExistEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public GetShopAdminInfoResponse getShopAdminInfo(Integer shopAdminId) {
		return GetShopAdminInfoResponse.from(shopAdminService.validById(shopAdminId));
	}

	@Override
	@Transactional
	public void updateShopAdminInfo(UpdateShopAdminInfoRequest request, Integer shopAdminId) {
		ShopAdmin shopAdmin = shopAdminService.validById(shopAdminId);
		String password = passwordEncoder.encode(request.password());
		shopAdmin.update(password, request.phoneNumber(), request.mallNumber(), request.email(), request.mallName());
	}

	@Override
	@Transactional
	public void deleteShopAdmin(Integer adminId, AdminRole adminRole, LocalDateTime localDateTime) {
		ShopAdmin shopAdmin = shopAdminService.validById(adminId);
		shopAdmin.delete(localDateTime);
		AdminAuth adminAuth = adminAuthService.findByAdminId(adminId, adminRole);
		adminAuth.logout();
	}
}
