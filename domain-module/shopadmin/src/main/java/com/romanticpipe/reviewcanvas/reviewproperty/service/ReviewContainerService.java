package com.romanticpipe.reviewcanvas.reviewproperty.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewContainer;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.ReviewContainerRepository;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ReviewContainerService {

	private static ReviewContainerRepository reviewContainerRepository;

	public ReviewContainer findByShopAdminId(Integer shopAdminId) {
		return reviewContainerRepository.findByShopAdminId(shopAdminId).orElseThrow();
	}
}
