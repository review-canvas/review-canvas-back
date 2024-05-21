package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.dto.ReviewInfo;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryRepository {

	Page<ReviewInfo> findAllReview(Long productId, Pageable pageable, ReviewFilter filter);
}
