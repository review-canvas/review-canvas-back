package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.enumeration.ReplyFilter;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForShopAdmin;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForUser;
import com.romanticpipe.reviewcanvas.enumeration.Score;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ReviewErrorCode;
import com.romanticpipe.reviewcanvas.exception.ReviewNotFoundException;
import com.romanticpipe.reviewcanvas.repository.ReviewRepository;
import com.romanticpipe.reviewcanvas.util.PageableUtils;
import com.romanticpipe.reviewcanvas.util.SortUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewRepository reviewRepository;

	public void save(Review review) {
		reviewRepository.save(review);
	}

	public PageResponse<Review> findAllByProductId(Long productId, PageableRequest pageableRequest,
												   ReviewFilterForUser filter) {
		Sort sort = SortUtils.getSort(pageableRequest.sort());
		Pageable pageable = PageableUtils.toPageable(pageableRequest, sort);
		return PageableUtils.toPageResponse(reviewRepository.findAllReview(productId, pageable, filter));
	}

	public PageResponse<Review> getReviewsInMyPage(Long userId, PageableRequest pageableRequest,
												   ReviewFilterForUser filter) {
		Sort sort = SortUtils.getSort(pageableRequest.sort());
		Pageable pageable = PageableUtils.toPageable(pageableRequest, sort);
		return PageableUtils.toPageResponse(reviewRepository.findAllByUserId(userId, pageable, filter));
	}

	public Review validById(long reviewId) {
		return reviewRepository.findById(reviewId)
			.orElseThrow(() -> new BusinessException(ReviewErrorCode.REVIEW_NOT_FOUND));
	}

	public Review validateUserInfoById(Long reviewId) {
		return reviewRepository.findById(reviewId)
			.orElseThrow(() -> new BusinessException(ReviewErrorCode.REVIEW_NOT_FOUND));
	}

	public Review validByIdAndUserId(Long reviewId, Long id) {
		return reviewRepository.findByIdAndUserId(reviewId, id)
			.orElseThrow(ReviewNotFoundException::new);
	}

	public PageResponse<Review> findAllByProductId(Long productId, PageableRequest pageableRequest,
												   EnumSet<ReviewFilterForShopAdmin> reviewFilters,
												   EnumSet<Score> score, EnumSet<ReplyFilter> replyFilters) {
		Sort sort = SortUtils.getSort(pageableRequest.sort());
		Pageable pageable = PageableUtils.toPageable(pageableRequest, sort);
		return PageableUtils.toPageResponse(
			reviewRepository.findAllByProductId(productId, pageable, reviewFilters, score, replyFilters)
		);
	}

}
