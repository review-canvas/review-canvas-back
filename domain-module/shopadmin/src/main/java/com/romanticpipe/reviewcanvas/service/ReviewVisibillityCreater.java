package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.ReviewVisibility;
import com.romanticpipe.reviewcanvas.repository.ReviewVisibilityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewVisibillityCreater {
	private final ReviewVisibilityRepository reviewVisibilityRepository;

	public void save(ReviewVisibility reviewVisibility) {
		reviewVisibilityRepository.save(reviewVisibility);
	}

}
