package com.romanticpipe.reviewcanvas.reviewproperty.service;

import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewContainer;
import com.romanticpipe.reviewcanvas.reviewproperty.exception.ReviewPropertyErrorCode;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.ReviewContainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewContainerService {
	private final ReviewContainerRepository reviewContainerRepository;

	public ReviewContainer validateByShopAdminId(Integer shopAdminId) {
		return reviewContainerRepository.findByShopAdminId(shopAdminId)
			.orElseThrow(() -> new BusinessException(ReviewPropertyErrorCode.REVIEW_CONTAINER_NOT_FOUND));
	}
}
