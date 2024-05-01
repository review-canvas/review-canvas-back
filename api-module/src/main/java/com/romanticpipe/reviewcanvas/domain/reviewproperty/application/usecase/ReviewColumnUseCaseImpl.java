package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateLayoutRequest;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewColumnService;
import com.romanticpipe.reviewcanvas.reviewproperty.service.ReviewLayoutService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewColumnUseCaseImpl implements ReviewColumnUseCase {

	private final ReviewColumnService reviewColumnService;

	@Override
	public ReviewColumn getColumn(Integer shopAdminId) {
		reviewColumnService.validateById(shopAdminId);
		return reviewColumnService.findById(shopAdminId).get();
	}
}
