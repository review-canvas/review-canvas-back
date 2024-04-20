package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewDesignRepository extends JpaRepository<ReviewDesign, Integer> {
	List<ReviewDesign> findAllByReviewDesignType(ReviewDesignType reviewDesignType);
}
