package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.exception.ReviewDesignNotFoundException;
import com.romanticpipe.reviewcanvas.repository.ReviewDesignRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewDesignValidator {

	private final ReviewDesignRepository reviewDesignRepository;

	public ReviewDesign validById(Long id) {
		return reviewDesignRepository.findById(id)
			.orElseThrow(() -> new ReviewDesignNotFoundException());
	}

}
