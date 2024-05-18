package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import java.time.LocalDateTime;

import com.romanticpipe.reviewcanvas.admin.domain.AdminRole;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.UpdateShopAdminInfoRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetShopAdminInfoResponse;

public interface ShopAdminUseCase {

	void signUp(SignUpRequest signUpRequest);

	boolean emailCheck(String email);

	GetShopAdminInfoResponse getShopAdminInfo(Integer shopAdminId);

	void updateShopAdminInfo(UpdateShopAdminInfoRequest request, Integer shopAdminId);

	void deleteShopAdmin(Integer adminId, AdminRole adminRole, LocalDateTime localDateTime);
}
