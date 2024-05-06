package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilter;
import com.romanticpipe.reviewcanvas.repository.ReviewRepository;
import com.romanticpipe.reviewcanvas.util.PageableUtils;
import com.romanticpipe.reviewcanvas.util.SortUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewReader {

	private final ReviewRepository reviewRepository;

	// TODO: 이미지가 추가되면 리뷰를 찾는 방법을 변경해야 합니다.
	public PageResponse<Review> findByProductId(Long productId, PageableRequest pageableRequest, ReviewFilter filter) {
		Sort sort = SortUtils.getSort(pageableRequest.sort());
		Pageable pageable = PageableUtils.toPageable(pageableRequest, sort);
		if (filter == ReviewFilter.ALL)
			return PageableUtils.toPageResponse(reviewRepository.findAllByProductId(productId, pageable));
		else if (filter == ReviewFilter.IMAGE_VIDEO)
			return PageableUtils.toPageResponse(
				reviewRepository.findAllByProductIdAndImageVideoUrlsIsNotNull(productId, pageable));
		else if (filter == ReviewFilter.GENERAL)
			return PageableUtils.toPageResponse(
				reviewRepository.findAllByProductIdAndImageVideoUrlsIsNull(productId, pageable));
		else
			throw new IllegalArgumentException("지원하지 않는 review filter 입니다." + filter);
	}

	public PageResponse<Review> findByUserId(String userId, PageableRequest pageableRequest) {
		Pageable pageable = PageableUtils.toPageable(pageableRequest, SortUtils.getSort(pageableRequest.sort()));
		return PageableUtils.toPageResponse(reviewRepository.findAllByUserId(userId, pageable));
	}

	public Review findByReviewId(long reviewId) {
		return reviewRepository.findById(reviewId);
	}
}
