package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.dto.ReviewInfo;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilter;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ReviewErrorCode;
import com.romanticpipe.reviewcanvas.repository.ReviewRepository;
import com.romanticpipe.reviewcanvas.util.PageableUtils;
import com.romanticpipe.reviewcanvas.util.SortUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewRepository reviewRepository;

	public void save(Review review) {
		reviewRepository.save(review);
	}

	public PageResponse<ReviewInfo> findAllByProductId(Long productId, PageableRequest pageableRequest,
													   ReviewFilter filter) {
		Sort sort = SortUtils.getSort(pageableRequest.sort());
		Pageable pageable = PageableUtils.toPageable(pageableRequest, sort);
		return PageableUtils.toPageResponse(reviewRepository.findAllReview(productId, pageable, filter));
	}

	public PageResponse<Review> findByUserId(String userId, PageableRequest pageableRequest) {
		Pageable pageable = PageableUtils.toPageable(pageableRequest, SortUtils.getSort(pageableRequest.sort()));
		return PageableUtils.toPageResponse(reviewRepository.findAllByUserId(userId, pageable));
	}

	public Review validById(long reviewId) {
		return reviewRepository.findById(reviewId)
			.orElseThrow(() -> new BusinessException(ReviewErrorCode.REVIEW_NOT_FOUND));
	}

	public ReviewInfo validateUserInfoById(Long reviewId) {
		return reviewRepository.findReviewInfoById(reviewId)
			.orElseThrow(() -> new BusinessException(ReviewErrorCode.REVIEW_NOT_FOUND));
	}
}
