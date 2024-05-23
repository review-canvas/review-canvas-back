package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.admin.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.admin.service.ShopAdminService;
import com.romanticpipe.reviewcanvas.cafe24.product.Cafe24ProductClient;
import com.romanticpipe.reviewcanvas.cafe24.product.Cafe24ProductDto;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.common.util.TransactionUtils;
import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetProductResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ProductUseCaseImpl implements ProductUseCase {

	private final Cafe24ProductClient cafe24ProductClient;
	private final ShopAdminService shopAdminService;
	private final ProductService productService;
	private final TransactionUtils transactionUtils;

	@Override
	public Product createProduct(String mallId, Long productNo) {
		Cafe24ProductDto cafe24ProductDto = cafe24ProductClient.getProduct(mallId, productNo);
		cafe24ProductDto.validateIsFullContent(mallId, productNo);

		return transactionUtils.executeInWriteTransaction(status -> {
			ShopAdmin shopAdmin = shopAdminService.validByMallId(mallId);
			Product product = new Product(productNo, cafe24ProductDto.product().productName(), shopAdmin.getId());
			return productService.save(product);
		});
	}

	@Override
	@Transactional(readOnly = true)
	public PageResponse<GetProductResponse> getProducts(JwtInfo jwtInfo, Integer loginAdminId,
														PageableRequest pageable) {
		shopAdminService.validateIsMyIdOrSuperAdmin(jwtInfo.adminId(), jwtInfo.adminRole(), loginAdminId);
		return productService.getProducts(jwtInfo.adminId(), pageable)
			.map(GetProductResponse::from);
	}
}
