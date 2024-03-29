package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase;

import org.springframework.stereotype.Component;

import com.romaintcpipe.reviewcanvas.service.ShopAdminCreater;
import com.romaintcpipe.reviewcanvas.service.ShopAdminValidator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ShopAdminUseCaseImpl implements ShopAdminUseCase {

	private final ShopAdminValidator shopAdminValidator;
	private final ShopAdminCreater shopAdminCreater;

}
