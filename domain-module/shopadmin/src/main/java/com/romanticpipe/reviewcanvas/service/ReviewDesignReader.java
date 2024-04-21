package com.romanticpipe.reviewcanvas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignType;
import com.romanticpipe.reviewcanvas.repository.ReviewDesignRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewDesignReader {
	private final ReviewDesignRepository reviewDesignRepository;

	public List<ReviewDesign> getGeneralThemeList(ReviewDesignType reviewDesignType) {
		return reviewDesignRepository.findAllByReviewDesignType(reviewDesignType);
	}
}
