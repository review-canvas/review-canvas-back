package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.repository.ReviewVisibilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewVisibilityReader {
	private final ReviewVisibilityRepository reviewVisibilityRepository;

	public List<String> getReviewVisibilityTitle() {
		return reviewVisibilityRepository.getReviewVisibilityTitles();
	}
}
