package com.romanticpipe.reviewcanvas.reviewproperty.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;
import com.romanticpipe.reviewcanvas.reviewproperty.exception.ReviewColumnNotFoundException;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.ReviewColumnRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewColumnService {

	private final ReviewColumnRepository reviewColumnRepository;

	public ReviewColumn validateByShopAdminId(Integer shopAdminId) {
		return reviewColumnRepository.findByShopAdminId(shopAdminId)
			.orElseThrow(() -> new ReviewColumnNotFoundException());
	}
}
