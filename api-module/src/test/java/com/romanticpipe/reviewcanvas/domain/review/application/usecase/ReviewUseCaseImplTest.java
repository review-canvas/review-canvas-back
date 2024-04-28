package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.TestProductFactory;
import com.romanticpipe.reviewcanvas.TestReviewFactory;
import com.romanticpipe.reviewcanvas.TestShopAdminFactory;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetAwaitReviewResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.enumeration.Direction;
import com.romanticpipe.reviewcanvas.service.ProductReader;
import com.romanticpipe.reviewcanvas.service.ProductValidator;
import com.romanticpipe.reviewcanvas.service.ReviewCreator;
import com.romanticpipe.reviewcanvas.service.ReviewReader;
import com.romanticpipe.reviewcanvas.service.ReviewValidator;
import com.romanticpipe.reviewcanvas.service.ShopAdminValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ReviewUseCaseImplTest {

	@Mock
	ShopAdminValidator shopAdminValidator;
	@Mock
	ProductValidator productValidator;
	@Mock
	ProductReader productReader;
	@Mock
	ReviewReader reviewReader;
	@Mock
	ReviewCreator reviewCreator;
	@Mock
	ReviewValidator reviewValidator;
	@InjectMocks
	ReviewUseCaseImpl reviewUseCase;

	@Nested
	@DisplayName("getReviewsByProductId 메소드는")
	class GetReviewsByProductIdTest {

		@Test
		@DisplayName("상품 아이디로 조회한 리뷰들을 dto로 변환하여 반환한다.")
		void it_returns_reviews_by_product_id() {
			// given
			var mallId = "mallId";
			var productNo = 1L;
			var productId = 2L;
			var product = TestProductFactory.createProduct(productId, productNo, 3);
			var pageableRequest = PageableRequest.of(0, 10, Direction.ASC);
			var review = TestReviewFactory.createReview(1L, 1L, 1L, "content", 5);
			var getReviewResponse = new PageResponse<>(0, 10, 0, List.of(review));
			given(productReader.findByMallIdAndProductNo(eq(mallId), eq(productNo)))
				.willReturn(Optional.of(product));
			given(reviewReader.findByProductId(eq(productId), eq(pageableRequest)))
				.willReturn(getReviewResponse);

			// when
			var result = reviewUseCase.getReviewsForUser(mallId, productNo, pageableRequest);

			// then
			Assertions.assertThat(result).isEqualTo(getReviewResponse.map(GetReviewResponse::from));
		}
	}

	@Nested
	@DisplayName("getAwaitReviewsByShopAdmin는")
	class GetAwaitReviewsByShopAdminTest {

		@Test
		@DisplayName("댓글 승인 시스템을 활성화한 shop에서 대기중인 리뷰를 dto로 변환하여 반환한다.")
		void it_returns_await_reviews_by_shopAdmin() {
			//given
			var shopAdminId = 1;
			var mallId = "mallId";
			var productNo = 1L;
			var productId = 2L;
			var shopAdmin = TestShopAdminFactory.createApproveShopAdmin(mallId, "test@naver.com", "test", "TEST_MALL",
				"IMAGE_URL", "MALL_001", "000-0000-0000", "BUSINESS_NUM");
			var product = TestProductFactory.createProduct(productId, productNo, shopAdminId);
			var pageableRequest = PageableRequest.of(0, 10, Direction.ASC);
			var review = TestReviewFactory.createWaitingReview(1L, 1L, 1L, "content", 5);
			var getReviewResponse = new PageResponse<>(0, 10, 0, List.of(review));
			given(productReader.findByMallIdAndProductNo(eq(mallId), eq(productNo)))
				.willReturn(Optional.of(product));
			given(reviewReader.findByProductId(eq(productId), eq(pageableRequest)))
				.willReturn(getReviewResponse);

			// when
			var result = reviewUseCase.getAwaitReviewsByShopAdmin(shopAdminId, pageableRequest);

			// then
			Assertions.assertThat(result).isEqualTo(getReviewResponse.map(GetAwaitReviewResponse::from));
		}

	}

}
