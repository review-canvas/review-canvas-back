package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.ReviewVisibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewVisibilityRepository extends JpaRepository<ReviewVisibility, Integer> {
	@Query(
		value = "SELECT COLUMN_NAME "
			+ "FROM INFORMATION_SCHEMA.COLUMNS "
			+ "WHERE TABLE_SCHEMA = 'review_canvas_db' "
			+ "  AND TABLE_NAME = 'review_visibility' "
			+ "  AND COLUMN_NAME NOT IN ('review_visibility_id') "
			+ "ORDER BY ORDINAL_POSITION;",
		nativeQuery = true
	)
	List<String> getReviewVisibilityTitles();
}
