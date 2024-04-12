package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import com.romanticpipe.reviewcanvas.config.ControllerTestSetup;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.ReviewUseCase;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("ReviewController 테스트")
@WebMvcTest(ReviewController.class)
class ReviewControllerTest extends ControllerTestSetup {

	private static final String BASE_URL = "/api/v1";

	@MockBean
	ReviewUseCase reviewUseCase;

	@Nested
	@DisplayName("상품 리뷰 조회 API는")
	class GetReviewsByProductId {

		@DisplayName("상품 아이디로 리뷰를 조회할 수 있다.")
		@Test
		void getReviewsByProductId() throws Exception {
			// given
			String productId = "product_no";
			long reviewId = 1L;
			String content = "content";
			int score = 5;
			var getReviewResponse = new GetReviewResponse(reviewId, content, score);
			var getReviewPageResponse = new PageResponse<>(0, 10, 0, List.of(getReviewResponse));
			given(reviewUseCase.getReviewsByProductId(eq(productId), any(PageableRequest.class)))
				.willReturn(getReviewPageResponse);

			// when
			ResultActions result = noSecurityMockMvc.perform(get(BASE_URL + "/products/" + productId + "/reviews"));

			// then
			result.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.content[0].reviewId").value(reviewId))
				.andExpect(jsonPath("$.data.content[0].content").value(content))
				.andExpect(jsonPath("$.data.content[0].score").value(score));
		}
	}

}
