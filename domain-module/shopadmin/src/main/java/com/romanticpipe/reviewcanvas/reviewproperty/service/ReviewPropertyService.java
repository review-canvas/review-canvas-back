package com.romanticpipe.reviewcanvas.reviewproperty.service;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewContainer;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewTitle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewPropertyService {

	private final ReviewLayoutService reviewLayoutService;
	private final ReviewContainerService reviewContainerService;
	private final ReviewColumnService reviewColumnService;
	private final ReviewTitleService reviewTitleService;


	public void createDefaultReviewProperty(Integer shopAdminId) {
		ReviewLayout reviewLayout = ReviewLayout.create(shopAdminId);
		ReviewContainer reviewContainer = ReviewContainer.create(shopAdminId);
		ReviewColumn reviewColumn = ReviewColumn.create(shopAdminId);
		ReviewTitle reviewTitle = ReviewTitle.createTitle(shopAdminId);
		ReviewTitle reviewTitleDescription = ReviewTitle.createDescription(shopAdminId);

		reviewLayoutService.save(reviewLayout);
		reviewContainerService.save(reviewContainer);
		reviewColumnService.save(reviewColumn);
		reviewTitleService.save(reviewTitle);
		reviewTitleService.save(reviewTitleDescription);
	}
}
