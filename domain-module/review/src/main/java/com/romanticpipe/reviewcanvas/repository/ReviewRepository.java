package com.romanticpipe.reviewcanvas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.romanticpipe.reviewcanvas.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryRepository {

	Optional<Review> findById(Long reviewId);

	Optional<Review> findByIdAndUserId(Long reviewId, Long userId);

	@Query("select count(r) from Review r join r.product p join ShopAdmin sa on p.shopAdminId = sa.id"
		+ " where sa.id = :shopAdminId and r.deletedAt = null ")
	Long countByShopAdminId(Integer shopAdminId);
}
