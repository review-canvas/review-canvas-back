package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.romanticpipe.reviewcanvas.config.ControllerTestSetup;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.ShopAdminUseCase;

@DisplayName("ShopAdminController 테스트")
@WebMvcTest(ShopAdminController.class)
class ShopAdminControllerTest extends ControllerTestSetup {

	private static final String BASE_URL = "/api/v1";

	@MockBean
	ShopAdminUseCase shopAdminUseCase;

	@Nested
	@DisplayName("리뷰 디자인 수정 API는")
	class UpdateReviewDesignTest {
		@DisplayName("리뷰 디자인 아이디로 리뷰 디자인을 수정할 수 있다.")
		@Test
		void updateReviewDesign() throws Exception {
			//given
			Long reviewDesignId = 1L;
			var updateReviewDesignRequest = "{\n"
				+ "  \"reviewDesignPosition\": \"REVIEW_LIST\",\n"
				+ "  \"themeName\": \"test\",\n"
				+ "  \"layoutType\": \"BOARD\",\n"
				+ "  \"padding\": \"0px\",\n"
				+ "  \"gap\": \"0px\",\n"
				+ "  \"boxShadowColor\": \"#000000\",\n"
				+ "  \"boxShadowWidth\": 0,\n"
				+ "  \"borderColor\": \"#ffffff\",\n"
				+ "  \"borderTransparency\": 0,\n"
				+ "  \"borderWidth\": 0,\n"
				+ "  \"pagingType\": \"NUMBER\",\n"
				+ "  \"pagingNumber\": 0,\n"
				+ "  \"textAlign\": \"left\",\n"
				+ "  \"pointColor\": \"#000000\",\n"
				+ "  \"pointType\": \"STAR\",\n"
				+ "  \"lineEllipsis\": 1,\n"
				+ "  \"reviewDesignUrl\": \"url\"\n"
				+ "}";
			//when
			ResultActions result = mockMvc.perform(
				patch(BASE_URL + "/shop-admin/review-design/" + reviewDesignId).contentType(
					MediaType.APPLICATION_JSON_VALUE).content(updateReviewDesignRequest));
			//then
			result.andExpect(status().isOk());
		}

	}
}
