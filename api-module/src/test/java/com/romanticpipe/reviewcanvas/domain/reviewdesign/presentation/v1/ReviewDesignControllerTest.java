package com.romanticpipe.reviewcanvas.domain.reviewdesign.presentation.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romanticpipe.reviewcanvas.config.ControllerTestSetup;
import com.romanticpipe.reviewcanvas.config.WithCustomAdmin;
import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignPosition;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignType;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.ReviewDesignUseCase;
import com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1.ReviewDesignController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("ReviewDesignController 테스트")
@WebMvcTest(ReviewDesignController.class)
class ReviewDesignControllerTest extends ControllerTestSetup {

	private static final String BASE_URL = "/api/v1";

	@MockBean
	ReviewDesignUseCase reviewDesignUseCase;
	ObjectMapper objectMapper = new ObjectMapper();

	@Nested
	@DisplayName("리뷰 디자인 수정 API는")
	@WithCustomAdmin
	class UpdateReviewDesignTest {
		@DisplayName("리뷰 디자인 아이디로 리뷰 디자인을 수정할 수 있다.")
		@Test
		void updateReviewDesign() throws Exception {
			//given
			var reviewDesignId = 1;
			var reviewDesign = ReviewDesign.builder()
				.reviewDesignPosition(ReviewDesignPosition.REVIEW_LIST)
				.reviewDesignType(ReviewDesignType.GENERAL)
				.themeName("test")
				.layoutType("BOARD")
				.padding("0px")
				.gap("0px")
				.boxShadowColor("#000000")
				.boxShadowWidth(0)
				.borderColor("#ffffff")
				.borderTransparency(0)
				.borderWidth(0)
				.pagingType("NUMBER")
				.pagingNumber(0)
				.textAlign("left")
				.pointColor("#000000")
				.pointType("STAR")
				.lineEllipsis(1)
				.reviewDesignUrl("url")
				.build();
			//when
			ResultActions result = securityMockMvc.perform(
				patch(BASE_URL + "/review-design/" + reviewDesignId).contentType(
						MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper.writeValueAsString(reviewDesign))
					.with(csrf()));
			//then
			result.andExpect(status().isOk());
		}
	}
}
