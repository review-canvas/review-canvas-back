package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.TestReviewFactory;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.enumeration.Direction;
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

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ReviewUseCaseImplTest {

	@Mock
	ShopAdminValidator shopAdminValidator;
	@Mock
	ProductValidator productValidator;
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
			var productId = "product_no";
			var pageableRequest = PageableRequest.of(0, 10, Direction.ASC);
			var review = TestReviewFactory.createReview(1L, "1", "1", "content", 5);
			var getReviewResponse = new PageResponse<>(0, 10, 0, List.of(review));
			given(reviewReader.findByProductId(eq(productId), eq(pageableRequest)))
				.willReturn(getReviewResponse);

			// when
			var result = reviewUseCase.getReviewsByProductId(productId, pageableRequest);

			// then
			Assertions.assertThat(result).isEqualTo(getReviewResponse.map(GetReviewResponse::from));
		}
	}

}