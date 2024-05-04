package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import com.romanticpipe.reviewcanvas.admin.service.AdminAuthCreator;
import com.romanticpipe.reviewcanvas.admin.service.ShopAdminCreator;
import com.romanticpipe.reviewcanvas.admin.service.ShopAdminValidator;
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
	AdminAuthCreator adminAuthCreator;
	@Mock
	ShopAdminCreator shopAdminCreator;
	@Mock
	ShopAdminValidator shopAdminValidator;
	@InjectMocks
	ShopAdminUseCaseImpl shopAdminUseCase;

}
