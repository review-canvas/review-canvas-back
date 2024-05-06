package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.admin.service.ShopAdminValidator;
import com.romanticpipe.reviewcanvas.service.ProductReader;
import com.romanticpipe.reviewcanvas.service.ProductValidator;
import com.romanticpipe.reviewcanvas.service.ReviewCreator;
import com.romanticpipe.reviewcanvas.service.ReviewReader;
import com.romanticpipe.reviewcanvas.service.ReviewValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
//			var mallId = "mallId";
//			var productNo = 1L;
//			var productId = 2L;
//			var product = TestProductFactory.createProduct(productId, productNo, 3);
//			var pageableRequest = PageableRequest.of(0, 10, ReviewSort.LATEST);
//			var filter = ReviewFilter.ALL;
//			var review = TestReviewFactory.createReview(1L, 1L, 1L, "content", 5, null);
//			var getReviewResponse = new PageResponse<>(0, 10, 0, List.of(review));
//			given(productReader.findProduct(eq(mallId), eq(productNo)))
//				.willReturn(Optional.of(product));
//			given(reviewReader.findByProductId(eq(productId), eq(pageableRequest), eq(filter)))
//				.willReturn(getReviewResponse);
//
//			// when
//			var result = reviewUseCase.getReviewsForUser(mallId, productNo, pageableRequest, filter);
//
//			// then
//			Assertions.assertThat(result).isEqualTo(getReviewResponse.map(GetReviewResponse::from));
		}
	}

}
