package com.romanticpipe.reviewcanvas.reviewproperty.service;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.exception.ReviewPropertyErrorCode;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.ReviewLayoutRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewLayoutService {

	private final ReviewLayoutRepository reviewLayoutRepository;

	@Transactional(readOnly = true)
	public ReviewLayout validById(Integer adminId) {
		Optional<ReviewLayout> reviewLayout = reviewLayoutRepository.findById(adminId);
		if(reviewLayout.isEmpty()) {
			throw new BusinessException(ReviewPropertyErrorCode.REVIEW_LAYOUT_NOT_FOUND);
		}
		return reviewLayout.get();
	}
}
