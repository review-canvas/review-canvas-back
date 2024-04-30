package com.romanticpipe.reviewcanvas.reviewproperty.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.ReviewLayoutRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewLayoutCreator {
	private final ReviewLayoutRepository reviewLayoutRepository;

	public ReviewLayout save(ReviewLayout reviewLayout) {
		return reviewLayoutRepository.save(reviewLayout);
	}
}