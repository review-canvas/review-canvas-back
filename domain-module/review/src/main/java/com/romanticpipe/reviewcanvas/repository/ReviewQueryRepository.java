package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.enumeration.ReplyFilter;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForShopAdmin;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForUser;
import com.romanticpipe.reviewcanvas.enumeration.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.EnumSet;

public interface ReviewQueryRepository {

	Page<Review> findAllReview(Long productId, Pageable pageable, ReviewFilterForUser filter);

	Page<Review> findAllByProductId(Long productId, Pageable pageable, EnumSet<ReviewFilterForShopAdmin> reviewFilters,
									EnumSet<Score> score, EnumSet<ReplyFilter> replyFilters);

	Page<Review> findAllByUserId(Long userId, Pageable pageable, ReviewFilterForUser filter);
}
