package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.dto.ReviewInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	@Query("SELECT r.id as reviewId, r.content as content, r.score as score, u.id as userId, u.nickName as nickname "
		+ "FROM Review r JOIN User u ON r.userId = u.id WHERE r.productId = :productId")
	Page<ReviewInfo> findAllReview(Long productId, Pageable pageable);

	@Query("SELECT r.id as reviewId, r.content as content, r.score as score, u.id as userId, u.nickName as nickname "
		+ "FROM Review r JOIN User u ON r.userId = u.id WHERE r.productId = :productId "
		+ "AND r.imageVideoUrls IS NULL")
	Page<ReviewInfo> findAllGeneralReview(Long productId, Pageable pageable);

	@Query("SELECT r.id as reviewId, r.content as content, r.score as score, u.id as userId, u.nickName as nickname "
		+ "FROM Review r JOIN User u ON r.userId = u.id WHERE r.productId = :productId "
		+ "AND r.imageVideoUrls IS NOT NULL")
	Page<ReviewInfo> findAllImageVideoReview(Long productId, Pageable pageable);

	Page<Review> findAllByUserId(String userId, Pageable pageable);

	Review findById(long reviewId);
}
