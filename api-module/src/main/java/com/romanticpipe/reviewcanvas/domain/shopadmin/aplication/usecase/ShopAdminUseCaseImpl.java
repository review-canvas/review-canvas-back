package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase;

import com.romanticpipe.reviewcanvas.common.security.TokenProvider;
import com.romanticpipe.reviewcanvas.domain.AdminAuth;
import com.romanticpipe.reviewcanvas.domain.AdminInterface;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignType;
import com.romanticpipe.reviewcanvas.domain.ReviewVisibility;
import com.romanticpipe.reviewcanvas.domain.Role;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.CheckLoginResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.GetReviewVisibilityTitleResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;
import com.romanticpipe.reviewcanvas.exception.AdminErrorCode;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.service.AdminAuthCreater;
import com.romanticpipe.reviewcanvas.service.AdminAuthRemover;
import com.romanticpipe.reviewcanvas.service.AdminAuthValidator;
import com.romanticpipe.reviewcanvas.service.ReviewVisibilityReader;
import com.romanticpipe.reviewcanvas.service.ReviewDesignReader;
import com.romanticpipe.reviewcanvas.service.ShopAdminCreator;
import com.romanticpipe.reviewcanvas.service.ShopAdminValidator;
import com.romanticpipe.reviewcanvas.service.SuperAdminValidator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
class ShopAdminUseCaseImpl implements ShopAdminUseCase {

	private final TokenProvider tokenProvider;
	private final BCryptPasswordEncoder passwordEncoder;
	private final AdminAuthCreater adminAuthCreater;
	private final AdminAuthRemover adminAuthRemover;
	private final AdminAuthValidator adminAuthValidator;
	private final ShopAdminCreator shopAdminCreator;
	private final ShopAdminValidator shopAdminValidator;
	private final SuperAdminValidator superAdminValidator;
	private final ReviewVisibilityReader reviewVisibilityReader;
	private final ReviewDesignReader reviewDesignReader;

	@Override
	@Transactional
	public LoginResponse login(String email, String password, Role role) {
		AdminInterface admin = role.equals(Role.SUPER_ADMIN_ROLE) ? superAdminValidator.validByEmail(email) :
			shopAdminValidator.validByEmail(email);

		if (!passwordEncoder.matches(password, admin.getPassword())) {
			throw new BusinessException(AdminErrorCode.ADMIN_WRONG_PASSWARD);
		}

		AdminAuth adminAuth = adminAuthValidator.findById(admin.getAdminAuthId());
		String accessToken = tokenProvider.createToken(admin);
		try {
			tokenProvider.validateToken(adminAuth.getRefreshToken());
		} catch (IllegalArgumentException | ExpiredJwtException | MalformedJwtException e) {
			tokenProvider.createRefreshToken(adminAuth, admin.getId());
		}
		return LoginResponse.from(admin.getId(), accessToken);

	}

	@Override
	@Transactional
	public void signUp(SignUpRequest signUpRequest, MultipartFile logoImage) {
		shopAdminValidator.isExistTheme(signUpRequest.reviewDesignId());

		AdminAuth adminAuth = new AdminAuth("");
		adminAuthCreater.save(adminAuth);

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
			.mallNumber(signUpRequest.mallNumber())
			.phoneNumber(signUpRequest.phoneNumber())
			.approveStatus(false)
			.shopInstallType(signUpRequest.shopInstallType())
			.installRequirement(signUpRequest.installRequirement())
			.selectedReviewDesignId(signUpRequest.reviewDesignId())
			.adminAuthId(adminAuth.getId())
			.build();

		shopAdminCreator.signUp(shopAdmin);
	}

	@Override
	@Transactional
	public void logout(AdminInterface admin) {
		adminAuthRemover.logout(admin.getAdminAuthId());
	}

	@Override
	@Transactional(readOnly = true)
	public CheckLoginResponse checkLoginSession(AdminInterface admin) {
		boolean isSuperAdmin = admin.getRole().equals(Role.SUPER_ADMIN_ROLE);

		admin = isSuperAdmin ? superAdminValidator.validById(admin.getId()) :
			shopAdminValidator.validById(admin.getId());
		return CheckLoginResponse.from(admin.getId(), admin.getRole().toString());
	}

	@Override
	@Transactional(readOnly = true)
	public LoginResponse reissuedAccessToken(String accessToken) {
		Claims claims = tokenProvider.getByClaimsExpiredToken(accessToken);
		AdminInterface admin = tokenProvider.getAdminByClaims(claims);
		tokenProvider.checkTokenExpired(admin.getAdminAuthId());
		accessToken = tokenProvider.createToken(admin);
		return LoginResponse.from(admin.getId(), accessToken);
	}

	@Override
	@Transactional(readOnly = true)
	public GetReviewVisibilityTitleResponse getReviewVisibilityTitle() {
		return GetReviewVisibilityTitleResponse.from(reviewVisibilityReader.getReviewVisibilityTitle());
	}

	@Override
	@Transactional(readOnly = true)
	public boolean emailCheck(String email) {
		return shopAdminValidator.isExistEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReviewDesign> getGeneralReviewThemeList() {
		return reviewDesignReader.getGeneralThemeList(ReviewDesignType.GENERAL);
	}
}
