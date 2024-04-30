package com.romanticpipe.reviewcanvas.reviewproperty.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewContainer;
import com.romanticpipe.reviewcanvas.reviewproperty.exception.ReviewPropertyErrorCode;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.ReviewContainerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewContainerService {
	private final ReviewContainerRepository reviewContainerRepository;

	public ReviewContainer save(ReviewContainer reviewContainer) {
		return reviewContainerRepository.save(reviewContainer);
	}

	public ReviewContainer validateByShopAdminId(Integer shopAdminId) {
		return reviewContainerRepository.findByShopAdminId(shopAdminId)
			.orElseThrow(() -> new BusinessException(ReviewPropertyErrorCode.REVIEW_CONTAINER_NOT_FOUND));
	}
}
