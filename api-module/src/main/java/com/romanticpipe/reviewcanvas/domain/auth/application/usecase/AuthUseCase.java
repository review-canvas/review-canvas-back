package com.romanticpipe.reviewcanvas.domain.auth.application.usecase;

import com.romanticpipe.reviewcanvas.admin.domain.AdminRole;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response.ShopAdminLoginResponse;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response.SuperAdminLoginResponse;

public interface AuthUseCase {

	ShopAdminLoginResponse shopAdminLogin(String email, String password);

	SuperAdminLoginResponse superAdminLogin(String email, String password);

	void logout(Integer adminId, AdminRole adminRole);
}
