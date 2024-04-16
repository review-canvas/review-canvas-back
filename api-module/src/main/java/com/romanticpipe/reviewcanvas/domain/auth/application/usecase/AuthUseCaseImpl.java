package com.romanticpipe.reviewcanvas.domain.auth.application.usecase;

import com.romanticpipe.reviewcanvas.common.security.TokenProvider;
import com.romanticpipe.reviewcanvas.common.security.exception.SecurityErrorCode;
import com.romanticpipe.reviewcanvas.domain.Admin;
import com.romanticpipe.reviewcanvas.domain.AdminAuth;
import com.romanticpipe.reviewcanvas.domain.AdminRole;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response.LoginResponse;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response.ReissueAccessTokenResponse;
import com.romanticpipe.reviewcanvas.exception.AdminErrorCode;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.service.AdminAuthValidator;
import com.romanticpipe.reviewcanvas.service.ShopAdminValidator;
import com.romanticpipe.reviewcanvas.service.SuperAdminValidator;
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
	private final AdminAuthValidator adminAuthValidator;
	private final ShopAdminValidator shopAdminValidator;
	private final SuperAdminValidator superAdminValidator;

	@Override
	@Transactional
	public LoginResponse login(String email, String password, AdminRole adminRole) {
		Admin admin = validateAdminByEmail(email, adminRole);
		if (!passwordEncoder.matches(password, admin.getPassword())) {
			throw new BusinessException(AdminErrorCode.ADMIN_WRONG_PASSWARD);
		}

		String accessToken = tokenProvider.createAccessToken(admin.getId(), admin.getRole());
		String refreshToken = tokenProvider.createRefreshToken(admin.getId(), admin.getRole());

		AdminAuth adminAuth = adminAuthValidator.findById(admin.getAdminAuthId());
		adminAuth.updateRefreshToken(refreshToken);

		return new LoginResponse(admin.getId(), accessToken, refreshToken);
	}

	@Override
	@Transactional
	public void logout(Long adminId, AdminRole adminRole) {
		Admin admin = validateAdminById(adminId, adminRole);
		AdminAuth adminAuth = adminAuthValidator.findById(admin.getAdminAuthId());
		adminAuth.logout();
	}

	@Override
	@Transactional(readOnly = true)
	public ReissueAccessTokenResponse reissuedAccessToken(String refreshToken) {
		Long adminId = tokenProvider.getAdminId(refreshToken);
		AdminAuth adminAuth = adminAuthValidator.findById(adminId);
		if (!Objects.equals(adminAuth.getRefreshToken(), refreshToken)) {
			throw new BusinessException(SecurityErrorCode.INVALID_TOKEN);
		}

		String newAccessToken = tokenProvider.createNewAccessTokenFromRefreshToken(refreshToken);
		return new ReissueAccessTokenResponse(newAccessToken);
	}

	private Admin validateAdminById(Long adminId, AdminRole adminRole) {
		if (Objects.equals(AdminRole.ROLE_SUPER_ADMIN, adminRole)) {
			return superAdminValidator.validByAuthId(adminId);
		}
		return shopAdminValidator.validByAuthId(adminId);
	}

	private Admin validateAdminByEmail(String email, AdminRole adminRole) {
		if (Objects.equals(AdminRole.ROLE_SUPER_ADMIN, adminRole)) {
			return superAdminValidator.validByEmail(email);
		}
		return shopAdminValidator.validByEmail(email);
	}
}
