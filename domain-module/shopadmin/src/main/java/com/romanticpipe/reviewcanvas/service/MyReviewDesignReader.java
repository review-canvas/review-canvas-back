package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.MyReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignType;
import com.romanticpipe.reviewcanvas.repository.MyReviewDesignRepository;
import com.romanticpipe.reviewcanvas.repository.ReviewDesignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyReviewDesignReader {

	private final MyReviewDesignRepository myReviewDesignRepository;

	public List<MyReviewDesign> getMyReviewDesignsByShopAdminId(Integer shopAdminId) {
		return myReviewDesignRepository.findByShopAdminId(shopAdminId);
	}

}
