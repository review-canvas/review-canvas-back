package com.romanticpipe.reviewcanvas.reviewproperty.service;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewContainer;
import com.romanticpipe.reviewcanvas.reviewproperty.exception.ReviewContainerNotFoundException;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.ReviewContainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewContainerService {
	private final ReviewContainerRepository reviewContainerRepository;

	public ReviewContainer save(ReviewContainer reviewContainer) {
		return reviewContainerRepository.save(reviewContainer);
	}

	public ReviewContainer validateByShopAdminId(Integer shopAdminId) {
		return reviewContainerRepository.findByShopAdminId(shopAdminId)
			.orElseThrow(ReviewContainerNotFoundException::new);
	}
}
