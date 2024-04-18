package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romanticpipe.reviewcanvas.TestReviewFactory;
import com.romanticpipe.reviewcanvas.config.ControllerTestSetup;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.ReviewUseCase;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;

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
			var review = TestReviewFactory.createReview(1L, productId, "1", "content", 5);
			var getReviewResponse = GetReviewResponse.from(review);
			var getReviewPageResponse = new PageResponse<>(0, 10, 0, List.of(getReviewResponse));
			given(reviewUseCase.getReviewsByProductId(eq(productId), any(PageableRequest.class)))
				.willReturn(getReviewPageResponse);

			// when
			ResultActions result = mockMvc.perform(get(BASE_URL + "/products/" + productId + "/reviews"));

			// then
			result.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.content[0].reviewId").value(review.getId()))
				.andExpect(jsonPath("$.data.content[0].content").value(review.getContent()))
				.andExpect(jsonPath("$.data.content[0].score").value(review.getScore()));
		}
	}

	@Nested
	@DisplayName("사용자 리뷰 조회 API는")
	class GetReviewsByUserId {

		@DisplayName("사용자 아이디로 리뷰를 조회할 수 있다.")
		@Test
		void getReviewsByUserId() throws Exception {
			// given
			String productId = "test_product_id";
			String userId = "test_user_id";
			var review = TestReviewFactory.createReview(1L, productId, userId, "test_content", 5);

			var getReviewResponse = GetReviewResponse.from(review);
			var getReviewPageResponse = new PageResponse<>(0, 10, 0, List.of(getReviewResponse));
			given(reviewUseCase.getReviewsByUserId(eq(userId), any(PageableRequest.class))).willReturn(
				getReviewPageResponse);

			// when
			ResultActions result = mockMvc.perform(get(BASE_URL + "/users/" + userId + "/reviews"));

			// then
			result.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.content[0].reviewId").value(review.getId()))
				.andExpect(jsonPath("$.data.content[0].content").value(review.getContent()))
				.andExpect(jsonPath("$.data.content[0].score").value(review.getScore()));
		}
	}

	@Nested
	@DisplayName("리뷰 생성 API는")
	class CreateReview {

		@DisplayName("상품 아이디로 리뷰를 생성할 수 있다.")
		@Test
		void createReview() throws Exception {
			// given
			String productId = "test_product_id";
			CreateReviewRequest createReviewRequest = new CreateReviewRequest("test_user_id", 5, "test_content");

			// when
			ResultActions result = mockMvc.perform(
				post(BASE_URL + "/products/" + productId + "/reviews")
					.content(new ObjectMapper().writeValueAsString(createReviewRequest))
					.contentType(MediaType.APPLICATION_JSON)
			);

			// then
			result.andExpect(status().isOk());
		}
	}

	@Nested
	@DisplayName("리뷰 수정 API는")
	class UpdateReview {

		@DisplayName("리뷰 아이디로 리뷰를 수정할 수 있다.")
		@Test
		void updateReview() throws Exception {
			// given
			String productId = "test_product_id";
			CreateReviewRequest createReviewRequest = new CreateReviewRequest("test_user_id", 5, "test_content");
			reviewUseCase.createReview(productId, createReviewRequest);
			UpdateReviewRequest updateReviewRequest = new UpdateReviewRequest("test_content_updated", 1);

			// when
			ResultActions result = mockMvc.perform(
				patch(BASE_URL + "/reviews/" + 1)
					.content(new ObjectMapper().writeValueAsString(updateReviewRequest))
					.contentType(MediaType.APPLICATION_JSON)
			);

			// then
			result.andExpect(status().isOk());
		}
	}
}
