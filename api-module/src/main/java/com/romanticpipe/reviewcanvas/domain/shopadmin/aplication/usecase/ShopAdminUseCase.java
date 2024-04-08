package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase;

import com.romanticpipe.reviewcanvas.domain.Role;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.CheckLoginResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;

public interface ShopAdminUseCase {

	LoginResponse login(String email, String password, Role role);

	void signUp(SignUpRequest signUpRequest);

	CheckLoginResponse checkLoginForAdmin();
}
