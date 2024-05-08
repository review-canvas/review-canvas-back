package com.romanticpipe.reviewcanvas.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.romanticpipe.reviewcanvas.repository.ReviewRepository;

@ExtendWith(MockitoExtension.class)
class ReviewReaderTest {

	@Mock
	ReviewRepository reviewRepository;
	@InjectMocks
	ReviewService reviewService;

	@Nested
	@DisplayName("findByProductId 메소드는")
	class FindByProductIdTest {

		@Test
		@DisplayName("상품 아이디로 조회한 리뷰들을 PageResponse로 반환한다.")
		void it_returns_reviews_by_product_id() {
			// given
			Long productId = 1L;
			//			PageableRequest pageableRequest = PageableRequest.of(0, 10, ReviewSort.LATEST);
			//			Review review = TestReviewFactory.createReview(2L, productId, 3L, "content", 5, null);
			//			PageImpl<Review> reviews = new PageImpl<>(List.of(review));
			//			given(reviewRepository.findAllReview(eq(productId), any(Pageable.class)))
			//				.willReturn(reviews);
			//
			//			// when
			//			var result = reviewService.findByProductId(productId, pageableRequest, ReviewFilter.ALL);
			//
			//			// then
			//			assertThat(result).isEqualTo(PageableUtils.toPageResponse(reviews));
		}
	}

}
