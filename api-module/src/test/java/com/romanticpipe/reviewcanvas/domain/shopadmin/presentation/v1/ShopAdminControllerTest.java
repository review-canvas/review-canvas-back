package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.romanticpipe.reviewcanvas.config.ControllerTestSetup;
import com.romanticpipe.reviewcanvas.config.WithCustomAdmin;
import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignPosition;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignType;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.ShopAdminUseCase;

@DisplayName("ShopAdminController 테스트")
@WebMvcTest(ShopAdminController.class)
class ShopAdminControllerTest extends ControllerTestSetup {

	private static final String BASE_URL = "/api/v1";

	@MockBean
	ShopAdminUseCase shopAdminUseCase;

	@Nested
	@DisplayName("리뷰 디자인 수정 API는")
	@WithCustomAdmin
	class UpdateReviewDesignTest {
		@DisplayName("리뷰 디자인 아이디로 리뷰 디자인을 수정할 수 있다.")
		@Test
		void updateReviewDesign() throws Exception {
			//given
			var reviewDesignId = 1;
			var updateReviewDesignRequest =
				"{\n" + "  \"reviewDesignPosition\": \"REVIEW_LIST\",\n" + "  \"themeName\": \"test\",\n"
					+ "  \"layoutType\": \"BOARD\",\n" + "  \"padding\": \"0px\",\n" + "  \"gap\": \"0px\",\n"
					+ "  \"boxShadowColor\": \"#000000\",\n" + "  \"boxShadowWidth\": 0,\n"
					+ "  \"borderColor\": \"#ffffff\",\n" + "  \"borderTransparency\": 0,\n" + "  \"borderWidth\": 0,\n"
					+ "  \"pagingType\": \"NUMBER\",\n" + "  \"pagingNumber\": 0,\n" + "  \"textAlign\": \"left\",\n"
					+ "  \"pointColor\": \"#000000\",\n" + "  \"pointType\": \"STAR\",\n" + "  \"lineEllipsis\": 1,\n"
					+ "  \"reviewDesignUrl\": \"url\"\n" + "}";
			//when
			ResultActions result = securityMockMvc.perform(
				patch(BASE_URL + "/shop-admin/review-design/" + reviewDesignId).contentType(
					MediaType.APPLICATION_JSON_VALUE).content(updateReviewDesignRequest).with(csrf()));
			//then
			result.andExpect(status().isOk());
		}

	}

	@Nested
	@DisplayName("적용 가능한 리뷰 디자인 조회 API 는")
	@WithCustomAdmin
	class GetApplicableReviewDesignTest {
		@DisplayName("현재 적용 가능한 리뷰를 조회할 수 있다.")
		@Test
		void getApplicableReviewDesign() throws Exception {
			//given
			Integer shopAdminId = 1;
			String reviewDesignPosition = "review_list";

			ReviewDesign reviewDesign1 = new ReviewDesign(1, ReviewDesignType.GENERAL, ReviewDesignPosition.REVIEW_LIST,
				"test_theme1", "test_layout", "test_padding", "test_gap", "test_color", 10, "test_color", 10, 10,
				"test_page", 10, "test_align", "test_color", "test_type", 10, "test_url");
			ReviewDesign reviewDesign2 = new ReviewDesign(1, ReviewDesignType.GENERAL, ReviewDesignPosition.REVIEW_LIST,
				"test_theme2", "test_layout", "test_padding", "test_gap", "test_color", 10, "test_color", 10, 10,
				"test_page", 10, "test_align", "test_color", "test_type", 10, "test_url");
			List<ReviewDesign> reviewDesigns = new ArrayList<>();
			reviewDesigns.add(reviewDesign1);
			reviewDesigns.add(reviewDesign2);

			given(shopAdminUseCase.getCustomReviewThemeList(eq(shopAdminId))).willReturn(reviewDesigns);

			//when
			ResultActions result = securityMockMvc.perform(
				get(BASE_URL + "/shop-admin/review-design/" + shopAdminId + "/" + reviewDesignPosition));

			//then
			result.andExpect(status().isOk());
		}
	}
}
