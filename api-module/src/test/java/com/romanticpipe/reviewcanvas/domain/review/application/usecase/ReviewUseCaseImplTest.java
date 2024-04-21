package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.romanticpipe.reviewcanvas.TestReviewFactory;
import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.domain.ReviewVisibility;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.domain.ShopInstallType;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.enumeration.Direction;
import com.romanticpipe.reviewcanvas.exception.AdminErrorCode;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ProductErrorCode;
import com.romanticpipe.reviewcanvas.exception.ReviewErrorCode;
import com.romanticpipe.reviewcanvas.service.ProductValidator;
import com.romanticpipe.reviewcanvas.service.ReviewCreator;
import com.romanticpipe.reviewcanvas.service.ReviewReader;
import com.romanticpipe.reviewcanvas.service.ReviewValidator;
import com.romanticpipe.reviewcanvas.service.ShopAdminValidator;

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

	@Nested
	@DisplayName("getReviewsByUserId 메서드 테스트")
	class GetReviewsByUserIdTest {

		@Test
		@DisplayName("성공 테스트")
		void successTest() {
			// given
			var userId = "test_user_id";
			var pageableRequest = PageableRequest.of(0, 10, Direction.ASC);
			var review = TestReviewFactory.createReview(1L, "1", "1", "content", 5);
			var getReviewResponse = new PageResponse<>(0, 10, 0, List.of(review));
			given(reviewReader.findByUserId(eq(userId), eq(pageableRequest)))
				.willReturn(getReviewResponse);

			// when
			var result = reviewUseCase.getReviewsByUserId(userId, pageableRequest);

			// then
			Assertions.assertThat(result).isEqualTo(getReviewResponse.map(GetReviewResponse::from));
		}

	}

	@Nested
	@DisplayName("updateReview 메서드 테스트")
	class UpdateReviewTest {

		@Test
		@DisplayName("성공 테스트")
		void successTest() {
			// given
			var reviewId = 1L;
			UpdateReviewRequest updateReviewRequest = new UpdateReviewRequest("test_content_updated", 1);
			var review = TestReviewFactory.createReview(1L, "1", "1", "content", 5);
			given(reviewValidator.validById(eq(reviewId))).willReturn(review);

			// when
			reviewUseCase.updateReview(reviewId, updateReviewRequest);
		}

		@Test
		@DisplayName("실패 테스트 : 유효하지 않은 리뷰 아이디")
		void failedByInvalidReviewId() {
			// given
			var reviewId = 1L;
			UpdateReviewRequest updateReviewRequest = new UpdateReviewRequest("test_content_updated", 1);

			// when
			given(reviewValidator.validById(eq(reviewId))).willThrow(
				new BusinessException(ReviewErrorCode.REVIEW_NOT_FOUND));

			// when
			assertThrows(BusinessException.class,
				() -> reviewUseCase.updateReview(reviewId, updateReviewRequest));
		}

	}

	@Nested
	@DisplayName("createReview 메서드 테스트")
	class CreateReviewTest {

		@Test
		@DisplayName("성공 테스트")
		void successTest() {
			// given
			var productId = "test_product_id";
			var shopAdminId = 1L;
			var createReviewRequest = new CreateReviewRequest("test_user_id", 5, "test_content");
			var product = new Product(productId, "test_name", shopAdminId);
			var shopAdmin = new ShopAdmin(ReviewVisibility.builder().build(), "test_mail", "test_pw", "test_name",
				"test_url", "test_num", "test_num", true, ShopInstallType.SELF_INSTALLATION, "test_req", 1L, 1L);

			// when
			given(productValidator.validByProductId(eq(productId))).willReturn(product);
			given(shopAdminValidator.validById(eq(shopAdminId))).willReturn(shopAdmin);

			// then
			reviewUseCase.createReview(productId, createReviewRequest);
		}

		@Test
		@DisplayName("실패 테스트 : 유효하지 않은 제품 아이디")
		void failedByInvalidProductId() {
			// given
			var productId = "test_product_id";
			var createReviewRequest = new CreateReviewRequest("test_user_id", 5, "test_content");

			// when
			given(productValidator.validByProductId(eq(productId))).willThrow(
				new BusinessException(ProductErrorCode.PRODUCT_NOT_FOUND));

			// then
			assertThrows(BusinessException.class,
				() -> reviewUseCase.createReview(productId, createReviewRequest));
		}

		@Test
		@DisplayName("실패 테스트 : 유효하지 않은 Shop admin 아이디")
		void failedByInvalidShopAdminId() {
			// given
			var productId = "test_product_id";
			var shopAdminId = 1L;
			var createReviewRequest = new CreateReviewRequest("test_user_id", 5, "test_content");
			var product = new Product(productId, "test_name", shopAdminId);
			var shopAdmin = new ShopAdmin(ReviewVisibility.builder().build(), "test_mail", "test_pw", "test_name",
				"test_url", "test_num", "test_num", true, ShopInstallType.SELF_INSTALLATION, "test_req", 1L, 1L);

			// when
			given(productValidator.validByProductId(eq(productId))).willReturn(product);
			given(shopAdminValidator.validById(eq(shopAdminId))).willThrow(
				new BusinessException(AdminErrorCode.ADMIN_NOT_FOUND));

			// then
			assertThrows(BusinessException.class,
				() -> reviewUseCase.createReview(productId, createReviewRequest));
		}

	}

}
