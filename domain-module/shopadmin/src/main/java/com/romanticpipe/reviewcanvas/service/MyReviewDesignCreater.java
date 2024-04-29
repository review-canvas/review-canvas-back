package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.MyReviewDesign;
import com.romanticpipe.reviewcanvas.repository.MyReviewDesignRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyReviewDesignCreater {
	private final MyReviewDesignRepository myReviewDesignRepository;

	public void save(MyReviewDesign myReviewDesign) {
		myReviewDesignRepository.save(myReviewDesign);
	}

}
