package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import com.romanticpipe.reviewcanvas.TestReviewDesignFactory;
import com.romanticpipe.reviewcanvas.TestShopAdminFactory;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignPosition;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.UpdateReviewDesignRequest;
import com.romanticpipe.reviewcanvas.service.AdminAuthCreater;
import com.romanticpipe.reviewcanvas.service.MyReviewDesignValidator;
import com.romanticpipe.reviewcanvas.service.ReviewDesignReader;
import com.romanticpipe.reviewcanvas.service.ReviewDesignValidator;
import com.romanticpipe.reviewcanvas.service.ReviewVisibilityReader;
import com.romanticpipe.reviewcanvas.service.ShopAdminCreator;
import com.romanticpipe.reviewcanvas.service.ShopAdminReader;
import com.romanticpipe.reviewcanvas.service.ShopAdminValidator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ShopAdminUseCaseImplTest {

	@Mock
	PasswordEncoder passwordEncoder;
	@Mock
	AdminAuthCreater adminAuthCreater;
	@Mock
	ShopAdminCreator shopAdminCreator;
	@Mock
	ShopAdminValidator shopAdminValidator;
	@Mock
	ShopAdminReader shopAdminReader;
	@Mock
	ReviewVisibilityReader reviewVisibilityReader;
	@Mock
	ReviewDesignReader reviewDesignReader;
	@Mock
	ReviewDesignValidator reviewDesignValidator;
	@Mock
	MyReviewDesignValidator myReviewDesignValidator;
	@InjectMocks
	ShopAdminUseCaseImpl shopAdminUseCase;

	@Nested
	@DisplayName("getReviewsByProductId 메소드는")
	class UpdateReviewDesignTest {
		@Test
		@DisplayName("리뷰 디자인 아이디로 조회한 리뷰 디자인을 dto에 맞게 수정한다.")
		void it_changes_review_design_by_review_design_id() {
			//given
			var shopAdminId = 2;
			var reviewDesignId = 1;
			var updateReviewDesignRequest = new UpdateReviewDesignRequest(
				ReviewDesignPosition.REVIEW_LIST, "test", "BOARD", "0px", "0px", "#000000", 0, "#ffffff", 0, 0,
				"NUMBER", 0, "left", "#000000", "STAR", 1, "urlurl"
			);
			var reviewDesign = TestReviewDesignFactory.createReviewDesign(1, "test", "BOARD", "0px", "0px", "#000000",
				0, "#ffffff", 0, 0,
				"NUMBER", 0, "left", "#000000", "STAR", 1, "url"
			);
			given(reviewDesignValidator.validById(eq(reviewDesignId))).willReturn(reviewDesign);

			//when
			shopAdminUseCase.updateReviewDesign(shopAdminId, reviewDesignId, updateReviewDesignRequest);

			//then
			assertThat(reviewDesign.getReviewDesignUrl()).isEqualTo(updateReviewDesignRequest.reviewDesignUrl());
		}

	}


	@Nested
	@DisplayName("getShopAdmin 메소드는")
	class GetShopAdminTest {
		@Test
		@DisplayName("Shop Admini Id로 Shop Admin을 조회한다.")
		void get_shop_admin_by_shop_admin_id() {
			//given
			var shopAdminId = 1;
			var shopAdmin = TestShopAdminFactory.createShopAdmin("test", "BOARD", "0px", "0px", "#000000",
				"0", "#ffffff", false,  "url"
			);
			given(shopAdminReader.findById(eq(shopAdminId))).willReturn(Optional.of(shopAdmin));

			//when
			var result = shopAdminUseCase.getShopAdmin(shopAdminId);

			//then
			Assertions.assertThat(result).isEqualTo(shopAdmin);
		}

	}

}
