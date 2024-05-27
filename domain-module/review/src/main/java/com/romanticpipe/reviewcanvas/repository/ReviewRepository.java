package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryRepository {

	Optional<Review> findById(Long reviewId);

	Optional<Review> findByIdAndUserId(Long reviewId, Long userId);
}
