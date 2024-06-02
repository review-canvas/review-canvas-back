package com.romanticpipe.reviewcanvas.domain.auth.application.usecase;

import com.romanticpipe.reviewcanvas.admin.domain.Admin;
import com.romanticpipe.reviewcanvas.admin.domain.AdminAuth;
import com.romanticpipe.reviewcanvas.admin.domain.AdminRole;
import com.romanticpipe.reviewcanvas.admin.exception.ShopAdminErrorCode;
import com.romanticpipe.reviewcanvas.admin.service.AdminAuthService;
import com.romanticpipe.reviewcanvas.admin.service.ShopAdminService;
import com.romanticpipe.reviewcanvas.admin.service.SuperAdminService;
import com.romanticpipe.reviewcanvas.common.security.TokenProvider;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response.ShopAdminLoginResponse;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response.SuperAdminLoginResponse;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthUseCaseImpl implements AuthUseCase {

	private final TokenProvider tokenProvider;
	private final PasswordEncoder passwordEncoder;
	private final AdminAuthService adminAuthService;
	private final ShopAdminService shopAdminService;
	private final SuperAdminService superAdminService;

	@Override
	@Transactional
	public ShopAdminLoginResponse shopAdminLogin(String email, String password) {
		return login(email, password, AdminRole.ROLE_SHOP_ADMIN, ShopAdminLoginResponse::new);
	}

	@Override
	@Transactional
	public SuperAdminLoginResponse superAdminLogin(String email, String password) {
		return login(email, password, AdminRole.ROLE_SUPER_ADMIN, SuperAdminLoginResponse::new);
	}

	private <T> T login(String email, String password, AdminRole role, LoginResponseFactory<T> responseFactory) {
		Admin admin = validateAdminByEmail(email, role);
		if (!passwordEncoder.matches(password, admin.getPassword())) {
			throw new BusinessException(ShopAdminErrorCode.ADMIN_WRONG_PASSWORD);
		}

		String accessToken = tokenProvider.createAccessToken(admin.getId(), admin.getRole());
		String refreshToken = tokenProvider.createRefreshToken(admin.getId(), admin.getRole());

		AdminAuth adminAuth = adminAuthService.findByAdminId(admin.getId(), admin.getRole());
		adminAuth.updateRefreshToken(refreshToken);

		return responseFactory.create(admin.getId(), accessToken, refreshToken);
	}

	@Override
	@Transactional
	public void logout(Integer adminId, AdminRole adminRole) {
		Admin admin = validateAdminById(adminId, adminRole);
		AdminAuth adminAuth = adminAuthService.findByAdminId(admin.getId(), admin.getRole());
		adminAuth.logout();
	}

	private Admin validateAdminById(Integer adminId, AdminRole adminRole) {
		if (Objects.equals(AdminRole.ROLE_SUPER_ADMIN, adminRole)) {
			return superAdminService.validById(adminId);
		}
		return shopAdminService.validateById(adminId);
	}

	private Admin validateAdminByEmail(String email, AdminRole adminRole) {
		if (Objects.equals(AdminRole.ROLE_SUPER_ADMIN, adminRole)) {
			return superAdminService.validByEmail(email);
		}
		return shopAdminService.validByEmail(email);
	}

	@FunctionalInterface
	private interface LoginResponseFactory<T> {
		T create(Integer adminId, String accessToken, String refreshToken);
	}
}
