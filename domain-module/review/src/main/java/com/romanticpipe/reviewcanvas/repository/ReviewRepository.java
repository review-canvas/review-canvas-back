package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	Page<Review> findAllByProductId(String productId, Pageable pageable);
}
