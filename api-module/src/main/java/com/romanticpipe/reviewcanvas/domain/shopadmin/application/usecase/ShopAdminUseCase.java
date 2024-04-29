package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.SignUpRequest;

public interface ShopAdminUseCase {

	void signUp(SignUpRequest signUpRequest);

	boolean emailCheck(String email);

}
