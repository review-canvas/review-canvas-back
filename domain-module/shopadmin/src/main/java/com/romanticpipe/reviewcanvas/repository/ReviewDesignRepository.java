package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewDesignRepository extends JpaRepository<ReviewDesign, Integer> {
	List<ReviewDesign> findAllByReviewDesignType(ReviewDesignType reviewDesignType);

	@Query("SELECT rd FROM ReviewDesign rd WHERE rd.reviewDesignType = 'GENERAL' "
		+ "OR (rd.reviewDesignType = 'CUSTOM' AND rd.shopAdminId = :shopAdminId)")
	List<ReviewDesign> findALlApplicableReviewDesigns(int shopAdminId);
}
