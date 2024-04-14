package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.romanticpipe.reviewcanvas.common.security.TokenProvider;
import com.romanticpipe.reviewcanvas.domain.AdminAuth;
import com.romanticpipe.reviewcanvas.domain.AdminInterface;
import com.romanticpipe.reviewcanvas.domain.ReviewVisibility;
import com.romanticpipe.reviewcanvas.domain.Role;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.CheckLoginResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ShopAdminErrorCode;
import com.romanticpipe.reviewcanvas.service.AdminAuthCreater;
import com.romanticpipe.reviewcanvas.service.AdminAuthValidator;
import com.romanticpipe.reviewcanvas.service.ShopAdminCreator;
import com.romanticpipe.reviewcanvas.service.ShopAdminRemover;
import com.romanticpipe.reviewcanvas.service.ShopAdminValidator;
import com.romanticpipe.reviewcanvas.service.SuperAdminValidator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ShopAdminUseCaseImpl implements ShopAdminUseCase {

	private final TokenProvider tokenProvider;
	private final AdminAuthCreater adminAuthCreater;
	private final AdminAuthValidator adminAuthValidator;
	private final ShopAdminCreator shopAdminCreator;
	private final ShopAdminValidator shopAdminValidator;
	private final SuperAdminValidator superAdminValidator;
	private final BCryptPasswordEncoder passwordEncoder;
	private final ShopAdminRemover shopAdminRemover;

	@Override
	@Transactional
	public LoginResponse login(String email, String password, Role role) {
		AdminInterface admin;
		if (role.equals(Role.SUPER_ADMIN_ROLE)) {
			admin = superAdminValidator.validByEmail(email);
		} else {
			admin = shopAdminValidator.validByEmail(email);
		}

		if (!passwordEncoder.matches(password, admin.getPassword())) {
			throw new BusinessException(ShopAdminErrorCode.ADMIN_WRONG_PASSWARD);
		}

		AdminAuth adminAuth = adminAuthValidator.findAdminAuthById(admin.getId());

		String accessToken = tokenProvider.createToken(admin);
		String refreshToken = adminAuth.getRefreshToken();

		if (tokenProvider.isExpiredToken(refreshToken)) {
			tokenProvider.createRefreshToken(adminAuth);
		}

		if (adminAuth.getId() == null) {
			adminAuthCreater.save(adminAuth);
		}

		return LoginResponse.from(admin.getId(), accessToken);

	}

	@Override
	@Transactional
	public void signUp(SignUpRequest signUpRequest, MultipartFile logoImage) {
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
			.password(passwordEncoder.encode(signUpRequest.password()))
			.name(signUpRequest.name())
			//			.logoImageUrl(signUpRequest.logoImageUrl())
			.mallNumber(signUpRequest.mallNumber())
			.phoneNumber(signUpRequest.phoneNumber())
			.approveStatus(false)
			.shopInstallType(signUpRequest.shopInstallType())
			.installRequirement(signUpRequest.installRequirement())
			.selectedReviewDesignId(signUpRequest.reviewDesignId())
			.build();

		shopAdminCreator.signUp(shopAdmin);
	}

	@Override
	@Transactional(readOnly = true)
	public CheckLoginResponse checkLoginForAdmin(AdminInterface admin) {
		if (admin.getRole().equals(Role.SUPER_ADMIN_ROLE)) {
			admin = superAdminValidator.validById(admin.getId());
		} else {
			admin = shopAdminValidator.validById(admin.getId());
		}
		return CheckLoginResponse.from(admin.getId(), admin.getRole().toString());
	}

	@Override
	@Transactional
	public void quit(Long id) {
		shopAdminRemover.quit(id);
	}
}
