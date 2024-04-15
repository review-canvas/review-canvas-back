package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase;

import org.springframework.web.multipart.MultipartFile;

import com.romanticpipe.reviewcanvas.domain.AdminInterface;
import com.romanticpipe.reviewcanvas.domain.Role;
import org.springframework.web.multipart.MultipartFile;

import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.CheckLoginResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.GetReviewVisibilityTitleResponse;
import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.response.LoginResponse;

public interface ShopAdminUseCase {

	LoginResponse login(String email, String password, Role role);

	void signUp(SignUpRequest signUpRequest, MultipartFile logoImage);

	void logout(AdminInterface admin);

	CheckLoginResponse checkLoginSession(AdminInterface admin);

	LoginResponse reissuedAccessToken(String accessToken);
	GetReviewVisibilityTitleResponse getReviewVisibilityTitle();
}
