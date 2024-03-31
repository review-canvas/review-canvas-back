package com.romanticpipe.reviewcanvas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	Page<Review> findAllByProductId(String productId, Pageable pageable);

	Page<Review> findAllByisChecked(boolean b);
}
