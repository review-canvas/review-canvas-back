package com.romanticpipe.reviewcanvas.reviewproperty.service;

import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.exception.ReviewPropertyErrorCode;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.ReviewLayoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewLayoutService {

	private final ReviewLayoutRepository reviewLayoutRepository;

	public ReviewLayout validateById(Integer shopAdminId) {
		return reviewLayoutRepository.findByShopAdminId(shopAdminId)
			.orElseThrow(() -> new BusinessException(ReviewPropertyErrorCode.REVIEW_LAYOUT_NOT_FOUND));
	}
}
