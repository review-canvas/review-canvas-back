package com.romanticpipe.reviewcanvas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.domain.ReviewItem;

public interface ReviewItemRepository extends JpaRepository<ReviewItem, Long> {
	
}
