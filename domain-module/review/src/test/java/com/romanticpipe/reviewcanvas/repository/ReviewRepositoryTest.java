package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.config.RepositoryTestSetup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ReviewRepository 테스트")
class ReviewRepositoryTest extends RepositoryTestSetup {

	@Nested
	@DisplayName("findAllByProductId 메소드 테스트")
	class FindAllByProductIdTest {

		@Nested
		@DisplayName("productId에 해당하는 리뷰가 없을 경우")
		class IfNoReviewExists {

			@Test
			@DisplayName("빈 페이지를 반환한다.")
			void returnEmptyPage() {
				// given
				String productId = "not_exist_product_id";

				// when
				var reviewPage = reviewRepository.findAllByProductId(productId, Pageable.unpaged());

				// then
				assertThat(reviewPage).isEmpty();
			}
		}

		@Nested
		@DisplayName("productId에 해당하는 리뷰가 여러개 있을 경우")
		class IfReviewsExist {

			@Test
			@DisplayName("리뷰 페이지를 반환한다.")
			void returnReviewPage() {
				// given
				String productId = "exist_product_id";
				String userId = "user_id";
				var reviewList = List.of(createReviewEntity(productId, userId), createReviewEntity(productId, userId));

				// when
				var reviewPage = reviewRepository.findAllByProductId(productId, Pageable.unpaged());

				// then
				assertThat(reviewPage).hasSize(2);
				assertThat(reviewPage.getContent()).containsAll(reviewList);
			}
		}
	}

}
