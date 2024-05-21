package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.dto.ReviewInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryRepository {

	Page<Review> findAllByUserId(String userId, Pageable pageable);

	Optional<Review> findById(Long reviewId);

	@Query("SELECT new com.romanticpipe.reviewcanvas.dto.ReviewInfo(r.id, r.content, r.score, u.id, u.nickName) "
		+ "FROM Review r JOIN User u ON r.userId = u.id WHERE r.id = :reviewId")
	Optional<ReviewInfo> findReviewInfoById(Long reviewId);
}
