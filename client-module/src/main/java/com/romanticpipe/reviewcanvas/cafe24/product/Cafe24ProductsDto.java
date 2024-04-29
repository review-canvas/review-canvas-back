package com.romanticpipe.reviewcanvas.cafe24.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.List;

public record Cafe24ProductsDto(@Getter List<Cafe24Product> products) {
	@JsonCreator
	public Cafe24ProductsDto {
	}
}
