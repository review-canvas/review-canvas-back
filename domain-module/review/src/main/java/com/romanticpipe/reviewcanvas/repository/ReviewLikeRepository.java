package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.ReviewLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {

	Integer countAllByReviewId(Long reviewId);

	Optional<ReviewLike> findByReviewIdAndUserIdAndShopAdminId(Long reviewId, Long userId, Integer shopAdminId);

	boolean existsByReviewIdAndUserId(Long reviewId, Long userId);

	boolean existsByReviewIdAndShopAdminId(Long reviewId, Integer shopAdminId);
}
