package com.romanticpipe.reviewcanvas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.repository.ReviewVisibilityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopAdminReader {
	private final ReviewVisibilityRepository reviewVisibilityRepository;

	public List<String> getReviewVisibilityTitle() {

		return null;
	}
}
