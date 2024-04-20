package com.romanticpipe.reviewcanvas.domain.auth.application.usecase;

import com.romanticpipe.reviewcanvas.domain.AdminRole;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response.LoginResponse;

public interface AuthUseCase {

	LoginResponse login(String email, String password, AdminRole adminRole);

	void logout(Integer adminId, AdminRole adminRole);
}
