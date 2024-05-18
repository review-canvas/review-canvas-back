package com.romanticpipe.reviewcanvas.common.converter;

import com.romanticpipe.reviewcanvas.enumeration.ReviewSort;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToReviewSortConverter implements Converter<String, ReviewSort> {

	@Override
	public ReviewSort convert(String source) {
		return ReviewSort.of(source);
	}
}
