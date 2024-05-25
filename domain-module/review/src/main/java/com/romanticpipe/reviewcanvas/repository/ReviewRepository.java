package com.romanticpipe.reviewcanvas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryRepository {

	Optional<Review> findById(Long reviewId);

	Optional<Review> findByIdAndUserId(Long reviewId, Long userId);
}
