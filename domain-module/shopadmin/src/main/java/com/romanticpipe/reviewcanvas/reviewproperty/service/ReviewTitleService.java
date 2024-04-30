package com.romanticpipe.reviewcanvas.reviewproperty.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewTitle;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewTitleType;
import com.romanticpipe.reviewcanvas.reviewproperty.exception.ReviewTitleNotFoundException;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.ReviewTitleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewTitleService {

	private final ReviewTitleRepository reviewTitleRepository;

	public ReviewTitle validTitleByShopAdminId(Integer shopAdminId) {
		return reviewTitleRepository.findByShopAdminIdAndReviewTitleType(shopAdminId, ReviewTitleType.TITLE)
			.orElseThrow(ReviewTitleNotFoundException::new);
	}

	public ReviewTitle validDescriptionByShopAdminId(Integer shopAdminId) {
		return reviewTitleRepository.findByShopAdminIdAndReviewTitleType(shopAdminId, ReviewTitleType.DESCRIPTION)
			.orElseThrow(ReviewTitleNotFoundException::new);
	}
}
