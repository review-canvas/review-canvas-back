package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewCreator {

	private final ReviewRepository reviewRepository;

	public void save(Review review) {
		System.out.println(review.getUserId() + " " + review.getContent() + " " + review.getScore());
		reviewRepository.save(review);
	}
}
