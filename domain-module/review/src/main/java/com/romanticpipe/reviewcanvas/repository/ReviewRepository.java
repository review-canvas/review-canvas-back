package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryRepository {

	Optional<Review> findById(Long reviewId);

	Optional<Review> findByIdAndUserId(Long reviewId, Long userId);

	@Query("select count(r) from Review r join r.product p join ShopAdmin sa on p.shopAdminId = sa.id"
		+ " where sa.id = :shopAdminId and r.deletedAt is null ")
	Long countByShopAdminId(Integer shopAdminId);
}
