package com.romanticpipe.reviewcanvas.reviewproperty.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewContainer;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.ReviewContainerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewContainerCreator {
	private final ReviewContainerRepository reviewContainerRepository;

	public ReviewContainer save(ReviewContainer reviewContainer) {
		return reviewContainerRepository.save(reviewContainer);
	}
}
