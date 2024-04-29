package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import com.romanticpipe.reviewcanvas.domain.AdminAuth;
import com.romanticpipe.reviewcanvas.domain.MyReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignType;
import com.romanticpipe.reviewcanvas.domain.ReviewVisibility;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.domain.*;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetReviewVisibilityTitleResponse;
import com.romanticpipe.reviewcanvas.service.AdminAuthCreater;
import com.romanticpipe.reviewcanvas.service.MyReviewDesignCreater;
import com.romanticpipe.reviewcanvas.service.ReviewDesignReader;
import com.romanticpipe.reviewcanvas.service.ReviewVisibilityReader;
import com.romanticpipe.reviewcanvas.service.ReviewVisibillityCreater;
import com.romanticpipe.reviewcanvas.service.ShopAdminCreator;
import com.romanticpipe.reviewcanvas.service.ShopAdminValidator;
import com.romanticpipe.reviewcanvas.repository.ReviewDesignRepository;
import com.romanticpipe.reviewcanvas.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
class ShopAdminUseCaseImpl implements ShopAdminUseCase {

	private final PasswordEncoder passwordEncoder;
	private final AdminAuthCreater adminAuthCreater;
	private final ShopAdminCreator shopAdminCreator;
	private final ShopAdminValidator shopAdminValidator;
	private final ReviewVisibillityCreater reviewVisibillityCreater;
	private final ReviewVisibilityReader reviewVisibilityReader;
	private final ReviewDesignReader reviewDesignReader;
	private final ReviewDesignValidator reviewDesignValidator;
	private final MyReviewDesignReader myReviewDesignReader;
	private final MyReviewDesignValidator myReviewDesignValidator;
	private final ReviewDesignRepository reviewDesignRepository;
	private final MyReviewDesignCreater myReviewDesignCreater;

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

		ReviewVisibility reviewVisibility = ReviewVisibility.create(shopAdmin.getId());
		MyReviewDesign myReviewDesign = MyReviewDesign.create(shopAdmin.getId());
		AdminAuth adminAuth = AdminAuth.createShopAdminAuth(shopAdmin.getId());
		reviewVisibillityCreater.save(reviewVisibility);
		myReviewDesignCreater.save(myReviewDesign);
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
}
