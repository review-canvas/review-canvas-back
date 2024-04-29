package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.exception.ReviewDesignNotFoundException;
import com.romanticpipe.reviewcanvas.repository.ReviewDesignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewDesignValidator {

	private final ReviewDesignRepository reviewDesignRepository;

	public ReviewDesign validById(Integer id) {
		return reviewDesignRepository.findById(id)
			.orElseThrow(() -> new ReviewDesignNotFoundException());
	}

}
