package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase;

import org.springframework.web.multipart.MultipartFile;

import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;

public interface ShopAdminUseCase {

	LoginResponse login(String email, String password);

	void signUp(SignUpRequest signUpRequest, MultipartFile logoImage);

	boolean emailCheck(String email);
}
