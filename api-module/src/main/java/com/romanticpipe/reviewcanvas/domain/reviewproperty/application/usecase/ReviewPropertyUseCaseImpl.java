package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.admin.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.admin.service.ShopAdminService;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetFontInfoResponse;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewPropertyForShopAdminResponse;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewPropertyForUserResponse;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewContainer;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewDesignView;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewTitle;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.FontBold;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.FontName;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewColumnService;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewContainerService;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewDesignViewService;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewLayoutService;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewPropertyUseCaseImpl implements ReviewPropertyUseCase {

	private final ShopAdminService shopAdminService;
	private final ReviewContainerService reviewContainerService;
	private final ReviewLayoutService reviewLayoutService;
	private final ReviewTitleService reviewTitleService;
	private final ReviewColumnService reviewColumnService;
	private final ReviewDesignViewService reviewDesignViewService;

	@Override
	@Transactional(readOnly = true)
	public GetReviewPropertyForShopAdminResponse getReviewPropertyForShopAdmin(Integer shopAdminId) {
		ReviewContainer reviewContainer = reviewContainerService.validateByShopAdminId(shopAdminId);
		ReviewLayout reviewLayout = reviewLayoutService.validateByShopAdminId(shopAdminId);
		ReviewTitle reviewTitle = reviewTitleService.validateTitleByShopAdminId(shopAdminId);
		ReviewColumn reviewColumn = reviewColumnService.validateByShopAdminId(shopAdminId);
		return GetReviewPropertyForShopAdminResponse.from(reviewLayout, reviewContainer, reviewTitle, reviewColumn);
	}

	@Override
	@Transactional(readOnly = true)
	public GetReviewPropertyForUserResponse getReviewPropertyForUser(String mallId) {
		ShopAdmin shopAdmin = shopAdminService.validByMallId(mallId);
		ReviewContainer reviewContainer = reviewContainerService.validateByShopAdminId(shopAdmin.getId());
		ReviewLayout reviewLayout = reviewLayoutService.validateByShopAdminId(shopAdmin.getId());
		ReviewTitle reviewTitle = reviewTitleService.validateTitleByShopAdminId(shopAdmin.getId());
		ReviewTitle reviewDescription = reviewTitleService.validateDescriptionByShopAdminId(shopAdmin.getId());
		ReviewColumn reviewColumn = reviewColumnService.validateByShopAdminId(shopAdmin.getId());
		ReviewDesignView reviewDesignView = reviewDesignViewService.validateByShopAdminId(shopAdmin.getId());
		return GetReviewPropertyForUserResponse.from(reviewLayout, reviewContainer, reviewTitle, reviewDescription,
			reviewColumn, reviewDesignView);
	}

	@Override
	public GetFontInfoResponse getFontInfo() {
		List<FontName> fontNames = Arrays.stream(FontName.values()).toList();
		List<FontBold> fontBolds = Arrays.stream(FontBold.values()).toList();
		return new GetFontInfoResponse(fontNames, fontBolds);
	}
}
