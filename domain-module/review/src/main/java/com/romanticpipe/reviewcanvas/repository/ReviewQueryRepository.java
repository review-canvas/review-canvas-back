package com.romanticpipe.reviewcanvas.repository;

import java.util.EnumSet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.enumeration.ReplyFilter;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForShopAdmin;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForUser;
import com.romanticpipe.reviewcanvas.enumeration.ReviewPeriod;
import com.romanticpipe.reviewcanvas.enumeration.Score;

public interface ReviewQueryRepository {

	Page<Review> findAllReview(Long productId, Pageable pageable, ReviewFilterForUser filter);

	Page<Review> findAllByProductId(Integer shopAdminId, Long productId, Pageable pageable, ReviewPeriod reviewPeriod,
		EnumSet<ReviewFilterForShopAdmin> reviewFilters,
		EnumSet<Score> score, EnumSet<ReplyFilter> replyFilters);

	Page<Review> findAllByUserId(Long userId, Pageable pageable, ReviewFilterForUser filter);

	Page<Review> findAllByUserIdAndProductId(Long userId, Long productId, Pageable pageable,
		ReviewFilterForUser filter);
}
