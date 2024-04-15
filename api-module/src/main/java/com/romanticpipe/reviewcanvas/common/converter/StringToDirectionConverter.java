package com.romanticpipe.reviewcanvas.common.converter;

import com.romanticpipe.reviewcanvas.enumeration.Direction;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDirectionConverter implements Converter<String, Direction> {

	@Override
	public Direction convert(String source) {
		try {
			return Direction.valueOf(source.toUpperCase());
		} catch (IllegalArgumentException e) {
			return Direction.NONE;
		}
	}
}
