package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewDesignRepository extends JpaRepository<ReviewDesign, Integer> {
	Optional<ReviewDesign> findById(Integer reviewDesignId);

	List<ReviewDesign> findAllByReviewDesignType(ReviewDesignType reviewDesignType);
}
