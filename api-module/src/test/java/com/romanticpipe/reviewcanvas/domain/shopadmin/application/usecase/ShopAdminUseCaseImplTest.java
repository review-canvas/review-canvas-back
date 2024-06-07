package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import com.romanticpipe.reviewcanvas.admin.service.AdminAuthService;
import com.romanticpipe.reviewcanvas.admin.service.ShopAdminService;
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
	AdminAuthService adminAuthService;
	@Mock
	ShopAdminService shopAdminService;
	@InjectMocks
	ShopAdminUseCaseImpl shopAdminUseCase;

}
