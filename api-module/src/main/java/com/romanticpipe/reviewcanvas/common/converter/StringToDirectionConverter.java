package com.romanticpipe.reviewcanvas.common.converter;

import com.romanticpipe.reviewcanvas.enumeration.Direction;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDirectionConverter implements Converter<String, Direction> {

	@Override
	public Direction convert(String source) {
		return Direction.of(source);
	}
}
