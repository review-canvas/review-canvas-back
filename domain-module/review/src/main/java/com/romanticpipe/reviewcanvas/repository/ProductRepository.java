package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p JOIN ShopAdmin s ON p.shopAdminId = s.id "
		+ "WHERE s.mallId = :mallId AND p.productNo = :productNo")
	Optional<Product> findByMallIdAndProductNo(String mallId, Long productNo);

	List<Product> findAllByShopAdminId(Integer shopAdminId);

	Page<Product> findAllByShopAdminId(Integer shopAdminId, Pageable pageable);
}
