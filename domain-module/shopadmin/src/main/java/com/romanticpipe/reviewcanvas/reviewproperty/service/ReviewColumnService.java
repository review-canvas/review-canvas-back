package com.romanticpipe.reviewcanvas.reviewproperty.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.exception.ReviewPropertyErrorCode;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.ReviewColumnRepository;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.ReviewLayoutRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewColumnService {

	private final ReviewColumnRepository reviewColumnRepository;

	public void validateById(Integer shopAdminId) {
		reviewColumnRepository.findByShopAdminId(shopAdminId)
			.orElseThrow(() -> new BusinessException(ReviewPropertyErrorCode.REVIEW_COLUMN_NOT_FOUND));
	}

	public Optional<ReviewColumn> findById(Integer shopAdminId) {
		return reviewColumnRepository.findById(shopAdminId);
	}
}
