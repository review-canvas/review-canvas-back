package com.romanticpipe.reviewcanvas.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.MyReviewDesign;
import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.repository.MyReviewDesignRepository;
import com.romanticpipe.reviewcanvas.util.PageableUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyReviewDesignReader {

	private final MyReviewDesignRepository myReviewDesignRepository;

	public PageResponse<MyReviewDesign> findByShopAdminId(long shopAdminId, PageableRequest pageableRequest) {
		Pageable pageable = PageableUtils.toPageable(pageableRequest);
		return PageableUtils.toPageResponse(myReviewDesignRepository.findAllByShopAdminId(shopAdminId, pageable));
	}

}
