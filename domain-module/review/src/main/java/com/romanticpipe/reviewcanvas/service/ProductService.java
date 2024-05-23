package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.repository.ProductRepository;
import com.romanticpipe.reviewcanvas.util.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public Optional<Product> findProduct(String mallId, Long productNo) {
		return productRepository.findByMallIdAndProductNo(mallId, productNo);
	}

	public Optional<Product> findProduct(Long productId) {
		return productRepository.findById(productId);
	}

	public List<Product> findProducts(Integer shopAdminId) {
		return productRepository.findAllByShopAdminId(shopAdminId);
	}

	public PageResponse<Product> getProducts(Integer shopAdminId, PageableRequest pageableRequest) {
		return PageableUtils.toPageResponse(
			productRepository.findAllByShopAdminId(shopAdminId, PageableUtils.toPageable(pageableRequest))
		);
	}

}
