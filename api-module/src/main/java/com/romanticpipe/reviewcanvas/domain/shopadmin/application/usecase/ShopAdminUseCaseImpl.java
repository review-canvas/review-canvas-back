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
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetShopInfoResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewLayoutService;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewPropertyService;
import com.romanticpipe.reviewcanvas.reviewproperty.service.TermsConsentService;
import com.romanticpipe.reviewcanvas.reviewproperty.service.TermsService;
import com.romanticpipe.reviewcanvas.service.ReviewService;

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
	private final ReviewService reviewService;
	private final ReviewLayoutService reviewLayoutService;

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
		return GetShopAdminInfoResponse.from(shopAdminService.validateById(shopAdminId));
	}

	@Override
	@Transactional
	public void updateShopAdminInfo(UpdateShopAdminInfoRequest request, Integer shopAdminId) {
		ShopAdmin shopAdmin = shopAdminService.validateById(shopAdminId);
		String password = passwordEncoder.encode(request.password());
		shopAdmin.update(password, request.phoneNumber(), request.mallNumber(), request.email(), request.mallName());
	}

	@Override
	@Transactional
	public void deleteShopAdmin(Integer adminId, AdminRole adminRole, LocalDateTime localDateTime) {
		ShopAdmin shopAdmin = shopAdminService.validateById(adminId);
		shopAdmin.delete(localDateTime);
		AdminAuth adminAuth = adminAuthService.findByAdminId(adminId, adminRole);
		adminAuth.logout();
	}

	@Override
	@Transactional(readOnly = true)
	public PageResponse<GetShopInfoResponse> getShopInfos(PageableRequest pageable) {
		PageResponse<ShopAdmin> shopAdmins = shopAdminService.findAll(pageable);

		return shopAdmins.map(shopAdmin -> GetShopInfoResponse.builder()
			.mallId(shopAdmin.getMallId())
			.createdAt(shopAdmin.getCreatedAt())
			.mallName(shopAdmin.getMallName())
			.mallNumber(shopAdmin.getMallNumber())
			.approveStatus(shopAdmin.isApproveStatus())
			.reviewsAmount(reviewService.countByShopAdminId(shopAdmin.getId()))
			.reviewLayoutDesign(
				reviewLayoutService.validateByShopAdminId(shopAdmin.getId()).getReviewLayoutDesign())
			.build());
	}
}
