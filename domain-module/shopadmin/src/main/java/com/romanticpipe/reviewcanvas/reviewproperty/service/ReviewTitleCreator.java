package com.romanticpipe.reviewcanvas.reviewproperty.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewTitle;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.ReviewTitleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewTitleCreator {
	private final ReviewTitleRepository reviewTitleRepository;

	public ReviewTitle save(ReviewTitle reviewTitle) {
		return reviewTitleRepository.save(reviewTitle);
	}
}
