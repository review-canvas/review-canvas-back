package com.romanticpipe.reviewcanvas.reviewproperty.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.ReviewColumnRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewColumnCreator {
	private final ReviewColumnRepository reviewColumnRepository;

	public ReviewColumn save(ReviewColumn reviewColumn) {
		return reviewColumnRepository.save(reviewColumn);
	}
}
