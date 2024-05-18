package com.romanticpipe.reviewcanvas.reviewproperty.service;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewDesignWrite;
import com.romanticpipe.reviewcanvas.reviewproperty.exception.ReviewDesignWriteNotFoundException;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.ReviewDesignWriteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewDesignWriteService {

	private final ReviewDesignWriteResponse reviewDesignWriteResponse;

	public ReviewDesignWrite validateByShopAdminId(Integer shopAdminId) {
		return reviewDesignWriteResponse.findByShopAdminId(shopAdminId)
			.orElseThrow(ReviewDesignWriteNotFoundException::new);
	}
}
