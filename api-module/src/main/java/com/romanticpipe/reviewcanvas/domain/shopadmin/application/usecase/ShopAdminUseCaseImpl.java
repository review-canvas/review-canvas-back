package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import com.romanticpipe.reviewcanvas.domain.*;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.UpdateReviewDesignRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetReviewVisibilityTitleResponse;
import com.romanticpipe.reviewcanvas.repository.ReviewDesignRepository;
import com.romanticpipe.reviewcanvas.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
class ShopAdminUseCaseImpl implements ShopAdminUseCase {

	private final PasswordEncoder passwordEncoder;
	private final AdminAuthCreater adminAuthCreater;
	private final ShopAdminCreator shopAdminCreator;
	private final ShopAdminValidator shopAdminValidator;
	private final ReviewVisibilityReader reviewVisibilityReader;
	private final ReviewDesignReader reviewDesignReader;
	private final ReviewDesignValidator reviewDesignValidator;
	private final MyReviewDesignReader myReviewDesignReader;
	private final MyReviewDesignValidator myReviewDesignValidator;
	private final ReviewDesignRepository reviewDesignRepository;

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
			//			.reviewVisibility(reviewVisibility)
			.email(signUpRequest.email())
			.password(passwordEncoder.encode(signUpRequest.password()))
			//			.name(signUpRequest.name())
			.mallNumber(signUpRequest.mallNumber())
			.phoneNumber(signUpRequest.phoneNumber())
			.approveStatus(false)
			.build();

		shopAdminCreator.signUp(shopAdmin);

		AdminAuth adminAuth = AdminAuth.createShopAdminAuth(shopAdmin.getId());
		adminAuthCreater.save(adminAuth);
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

	@Override
	@Transactional(readOnly = true)
	public List<ReviewDesign> getCustomReviewThemeList(Integer shopAdminId) {
		List<MyReviewDesign> myReviewDesigns = myReviewDesignReader.getMyReviewDesignsByShopAdminId(shopAdminId);
		List<ReviewDesign> reviewDesigns = new ArrayList<>();
		for (MyReviewDesign myReviewDesign : myReviewDesigns) {
			reviewDesigns.add(reviewDesignValidator.validById(myReviewDesign.getReviewDesignId()));
		}
		return reviewDesigns;
	}

	@Override
	@Transactional
	public void updateReviewDesign(Integer shopAdminId, Integer reviewDesignId,
								   UpdateReviewDesignRequest updateReviewDesignRequest) {
		ReviewDesign reviewDesign = reviewDesignValidator.validById(reviewDesignId);
		myReviewDesignValidator.validateIsMyDesign(shopAdminId, reviewDesignId);

		reviewDesign.update(
			updateReviewDesignRequest.reviewDesignPosition(),
			updateReviewDesignRequest.themeName(),
			updateReviewDesignRequest.layoutType(),
			updateReviewDesignRequest.padding(),
			updateReviewDesignRequest.gap(),
			updateReviewDesignRequest.boxShadowColor(),
			updateReviewDesignRequest.boxShadowWidth(),
			updateReviewDesignRequest.borderColor(),
			updateReviewDesignRequest.borderTransparency(),
			updateReviewDesignRequest.borderWidth(),
			updateReviewDesignRequest.pagingType(),
			updateReviewDesignRequest.pagingNumber(),
			updateReviewDesignRequest.textAlign(),
			updateReviewDesignRequest.pointColor(),
			updateReviewDesignRequest.pointType(),
			updateReviewDesignRequest.lineEllipsis(),
			updateReviewDesignRequest.reviewDesignUrl());
	}
}
