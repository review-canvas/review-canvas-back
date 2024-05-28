package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import com.romanticpipe.reviewcanvas.admin.domain.AdminRole;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.UpdateShopAdminInfoRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetShopAdminInfoResponse;

import java.time.LocalDateTime;

public interface ShopAdminUseCase {

	void signUp(SignUpRequest signUpRequest);

	boolean emailCheck(String email);

	GetShopAdminInfoResponse getShopAdminInfo(Integer shopAdminId);

	void updateShopAdminInfo(UpdateShopAdminInfoRequest request, Integer shopAdminId);

	void deleteShopAdmin(Integer adminId, AdminRole adminRole, LocalDateTime localDateTime);
}
