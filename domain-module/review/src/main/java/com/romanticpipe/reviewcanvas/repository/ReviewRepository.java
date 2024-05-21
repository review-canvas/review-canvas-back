package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryRepository {

	Page<Review> findAllByUserId(String userId, Pageable pageable);

	Optional<Review> findById(Long reviewId);
}
