package com.romaintcpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romaintcpipe.reviewcanvas.repository.ShopAdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopAdminCreater {
	private final ShopAdminRepository shopAdminRepository;

}
