package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetShopAdminInfoResponse;

public interface ShopAdminUseCase {

	void signUp(SignUpRequest signUpRequest);

	boolean emailCheck(String email);

	GetShopAdminInfoResponse getShopAdminInfo(Integer shopAdminId);
}
