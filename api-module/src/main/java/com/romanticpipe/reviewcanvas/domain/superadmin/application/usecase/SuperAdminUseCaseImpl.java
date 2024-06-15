package com.romanticpipe.reviewcanvas.domain.superadmin.application.usecase;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.admin.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.admin.service.ShopAdminService;
import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.domain.superadmin.application.usecase.response.GetShopInfoResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewLayoutService;
import com.romanticpipe.reviewcanvas.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SuperAdminUseCaseImpl implements SuperAdminUseCase {

	private final ShopAdminService shopAdminService;
	private final ReviewService reviewService;
	private final ReviewLayoutService reviewLayoutService;

	@Override
	@Transactional(readOnly = true)
	public PageResponse<GetShopInfoResponse> getShopInfos(PageableRequest pageable) {
		PageResponse<ShopAdmin> shopAdmins = shopAdminService.findAll(pageable);
		List<Review> reviews = reviewService.findAll();

		return shopAdmins.map(shopAdmin -> this.convertToGetShopInfoResponse(shopAdmin, reviews));
	}

	private Integer countReviews(ShopAdmin shopAdmin, List<Review> reviews) {
		return Math.toIntExact(reviews.stream()
			.filter(review ->
				(review.getShopAdminId() != null && review.getShopAdminId().equals(shopAdmin.getId()))
					|| (review.getUser() != null && review.getUser().getMallId().equals(shopAdmin.getMallId())))
			.count());
	}

	private GetShopInfoResponse convertToGetShopInfoResponse(ShopAdmin shopAdmin, List<Review> reviews) {
		return GetShopInfoResponse.builder()
			.mallId(shopAdmin.getMallId())
			.createdAt(shopAdmin.getCreatedAt())
			.mallName(shopAdmin.getMallName())
			.mallNumber(shopAdmin.getMallNumber())
			.approveStatus(shopAdmin.isApproveStatus())
			.reviewsAmount(countReviews(shopAdmin, reviews))
			.reviewLayoutDesign(
				reviewLayoutService.validateByShopAdminId(shopAdmin.getId()).getReviewLayoutDesign())
			.build();
	}
}
