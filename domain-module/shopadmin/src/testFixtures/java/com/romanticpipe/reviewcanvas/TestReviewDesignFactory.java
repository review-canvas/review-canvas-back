package com.romanticpipe.reviewcanvas;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignPosition;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignType;
import org.springframework.test.util.ReflectionTestUtils;

public final class TestReviewDesignFactory {

	public static ReviewDesign createReviewDesign(
		String themeName, String layoutType, String padding, String gap, String boxShadowColor, int boxShadowWidth,
		String borderColor, int borderTransparency, int borderWidth, String pagingType, int pagingNumber,
		String textAlign, String pointColor, String pointType, int lineEllipsis, String reviewDesignUrl) {
		return new ReviewDesign(1, ReviewDesignType.GENERAL,
			ReviewDesignPosition.REVIEW_LIST, themeName, layoutType,
			padding, gap, boxShadowColor, boxShadowWidth, borderColor, borderTransparency, borderWidth, pagingType,
			pagingNumber, textAlign, pointColor, pointType, lineEllipsis, reviewDesignUrl);
	}

	public static ReviewDesign createReviewDesign(
		Integer reviewDesignId,
		String themeName, String layoutType, String padding, String gap, String boxShadowColor,
		int boxShadowWidth, String borderColor, int borderTransparency, int borderWidth, String pagingType,
		int pagingNumber, String textAlign, String pointColor, String pointType, int lineEllipsis,
		String reviewDesignUrl) {
		ReviewDesign reviewDesign = new ReviewDesign(1, ReviewDesignType.GENERAL, ReviewDesignPosition.REVIEW_LIST,
			themeName, layoutType,
			padding, gap, boxShadowColor, boxShadowWidth, borderColor, borderTransparency, borderWidth, pagingType,
			pagingNumber, textAlign, pointColor, pointType, lineEllipsis, reviewDesignUrl);
		ReflectionTestUtils.setField(reviewDesign, "id", reviewDesignId);
		return reviewDesign;
	}
}
