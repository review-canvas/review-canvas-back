package com.romanticpipe.reviewcanvas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.romanticpipe.reviewcanvas.domain.ReviewVisibility;

public interface ReviewVisibilityRepository extends JpaRepository<ReviewVisibility, Long> {
	@Query(
		value = "SELECT COLUMN_NAME\n"
			+ "FROM INFORMATION_SCHEMA.COLUMNS\n"
			+ "WHERE TABLE_SCHEMA = 'review_canvas_db'\n"
			+ "  AND TABLE_NAME = 'review_visibility'\n"
			+ "  AND COLUMN_NAME NOT IN ('review_visibility_id')\n"
			+ "ORDER BY ORDINAL_POSITION;",
		nativeQuery = true
	)
	List<String> getReviewVisibilityTitles();
}
