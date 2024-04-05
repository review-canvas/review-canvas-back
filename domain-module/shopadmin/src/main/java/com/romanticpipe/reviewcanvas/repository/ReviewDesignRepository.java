package com.romanticpipe.reviewcanvas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;

public interface ReviewDesignRepository extends JpaRepository<ReviewDesign, Long> {
	Optional<ReviewDesign> findById(Long reviewDesignId);
}
