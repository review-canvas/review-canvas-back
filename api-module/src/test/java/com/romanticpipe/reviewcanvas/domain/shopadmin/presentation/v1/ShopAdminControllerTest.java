package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.romanticpipe.reviewcanvas.config.ControllerTestSetup;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.ShopAdminUseCase;

@DisplayName("ShopAdminController 테스트")
@WebMvcTest(ShopAdminController.class)
class ShopAdminControllerTest extends ControllerTestSetup {

	private static final String BASE_URL = "/api/v1";

	@MockBean
	ShopAdminUseCase shopAdminUseCase;

}
