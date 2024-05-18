package com.romanticpipe.reviewcanvas.reviewproperty.service;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewDesignView;
import com.romanticpipe.reviewcanvas.reviewproperty.exception.ReviewDesignViewNotFoundException;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.ReviewDesignViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewDesignViewService {

	private final ReviewDesignViewRepository reviewDesignViewRepository;


	public ReviewDesignView validateByShopAdminId(Integer shopAdminId) {
		return reviewDesignViewRepository.findByShopAdminId(shopAdminId)
			.orElseThrow(ReviewDesignViewNotFoundException::new);
	}

	public ReviewDesignView save(ReviewDesignView reviewDesignView) {
		return reviewDesignViewRepository.save(reviewDesignView);
	}
}
