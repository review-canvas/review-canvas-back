package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.romanticpipe.reviewcanvas.config.ControllerTestSetup;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.ReviewColumnUseCase;

@DisplayName("ReviewColumnController 테스트")
@WebMvcTest(ReviewColumnController.class)
class ReviewColumnControllerTest extends ControllerTestSetup {

	private static final String BASE_URL = "/api/v1";

	@MockBean
	ReviewColumnUseCase reviewColumnUseCase;


}
