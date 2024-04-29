package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import com.romanticpipe.reviewcanvas.admin.service.AdminAuthCreater;
import com.romanticpipe.reviewcanvas.admin.service.ShopAdminCreator;
import com.romanticpipe.reviewcanvas.admin.service.ShopAdminValidator;
import com.romanticpipe.reviewcanvas.service.ReviewDesignReader;
import com.romanticpipe.reviewcanvas.service.ReviewVisibilityReader;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

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
	ReviewVisibilityReader reviewVisibilityReader;
	@Mock
	ReviewDesignReader reviewDesignReader;
	@InjectMocks
	ShopAdminUseCaseImpl shopAdminUseCase;

}
