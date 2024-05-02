package com.romanticpipe.reviewcanvas.reviewproperty.service;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.exception.ReviewLayoutNotFoundException;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.ReviewLayoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewLayoutService {

	private final ReviewLayoutRepository reviewLayoutRepository;

	public ReviewLayout validateById(Integer shopAdminId) {
		return reviewLayoutRepository.findByShopAdminId(shopAdminId)
			.orElseThrow(ReviewLayoutNotFoundException::new);
	}
}
