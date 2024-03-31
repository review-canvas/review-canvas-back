package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewCreator {

	private final ReviewRepository reviewRepository;

	public void save(String productId, int score, String content) {
		reviewRepository.save(new Review(productId, content, score));
	}
}
