package com.romanticpipe.reviewcanvas.domain.auth.application.usecase;

import com.romanticpipe.reviewcanvas.domain.AdminRole;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response.LoginResponse;
import com.romanticpipe.reviewcanvas.domain.auth.application.usecase.response.ReissueAccessTokenResponse;

public interface AuthUseCase {

	LoginResponse login(String email, String password, AdminRole adminRole);

	void logout(Long adminId, AdminRole adminRole);

	ReissueAccessTokenResponse reissuedAccessToken(String refreshToken);
}
