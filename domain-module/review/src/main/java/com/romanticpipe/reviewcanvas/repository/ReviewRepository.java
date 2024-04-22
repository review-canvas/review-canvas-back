package com.romanticpipe.reviewcanvas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.romanticpipe.reviewcanvas.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	Page<Review> findAllByProductId(String productId, Pageable pageable);

	Page<Review> findAllByUserId(String userId, Pageable pageable);

	Review findById(long reviewId);

	@Query("select r from Review r inner join Product p "
		+ "on r.productId = p.id inner join ShopAdmin sa "
		+ "on p.shopAdminId = sa.id "
		+ "where sa.id = :shopAdminId and r.status = 'WAITING'")
	Page<Review> findAllByShopAdminId(@Param("shopAdminId") long shopAdminId, Pageable pageable);

}
