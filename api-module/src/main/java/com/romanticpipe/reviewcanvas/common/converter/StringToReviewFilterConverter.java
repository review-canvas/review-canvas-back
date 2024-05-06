package com.romanticpipe.reviewcanvas.common.converter;

import com.romanticpipe.reviewcanvas.enumeration.ReviewFilter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToReviewFilterConverter implements Converter<String, ReviewFilter> {

	@Override
	public ReviewFilter convert(String source) {
		return ReviewFilter.of(source);
	}
}
