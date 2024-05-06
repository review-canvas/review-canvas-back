package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.TestReviewFactory;
import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilter;
import com.romanticpipe.reviewcanvas.enumeration.ReviewSort;
import com.romanticpipe.reviewcanvas.repository.ReviewRepository;
import com.romanticpipe.reviewcanvas.util.PageableUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ReviewReaderTest {

	@Mock
	ReviewRepository reviewRepository;
	@InjectMocks
	ReviewReader reviewReader;

	@Nested
	@DisplayName("findByProductId 메소드는")
	class FindByProductIdTest {

		@Test
		@DisplayName("상품 아이디로 조회한 리뷰들을 PageResponse로 반환한다.")
		void it_returns_reviews_by_product_id() {
			// given
			Long productId = 1L;
			PageableRequest pageableRequest = PageableRequest.of(0, 10, ReviewSort.LATEST);
			Review review = TestReviewFactory.createReview(2L, productId, 3L, "content", 5, null);
			PageImpl<Review> reviews = new PageImpl<>(List.of(review));
			given(reviewRepository.findAllByProductId(eq(productId), any(Pageable.class)))
				.willReturn(reviews);

			// when
			var result = reviewReader.findByProductId(productId, pageableRequest, ReviewFilter.ALL);

			// then
			assertThat(result).isEqualTo(PageableUtils.toPageResponse(reviews));
		}
	}

}
