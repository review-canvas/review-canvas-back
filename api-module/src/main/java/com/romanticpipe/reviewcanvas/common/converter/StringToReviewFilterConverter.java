package com.romanticpipe.reviewcanvas.common.converter;

import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToReviewFilterConverter implements Converter<String, ReviewFilterForUser> {

	@Override
	public ReviewFilterForUser convert(String source) {
		return ReviewFilterForUser.of(source);
	}
}
