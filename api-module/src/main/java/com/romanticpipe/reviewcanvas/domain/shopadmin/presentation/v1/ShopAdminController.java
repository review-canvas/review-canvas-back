package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.ShopAdminUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class ShopAdminController implements ShopAdminApi {

	private final ShopAdminUseCase shopAdminUseCase;

}
