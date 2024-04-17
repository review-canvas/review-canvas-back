package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignType;
import com.romanticpipe.reviewcanvas.repository.ReviewDesignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewDesignReader {
	private final ReviewDesignRepository reviewDesignRepository;

	public List<ReviewDesign> getGeneralThemeList(ReviewDesignType reviewDesignType) {
		return reviewDesignRepository.findAllByReviewDesignType(reviewDesignType);
	}
}
