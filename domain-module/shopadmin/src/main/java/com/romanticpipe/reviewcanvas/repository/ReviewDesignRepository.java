package com.romanticpipe.reviewcanvas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignType;

public interface ReviewDesignRepository extends JpaRepository<ReviewDesign, Long> {
	Optional<ReviewDesign> findById(Long reviewDesignId);

	List<ReviewDesign> findAllByReviewDesignType(ReviewDesignType reviewDesignType);
}
