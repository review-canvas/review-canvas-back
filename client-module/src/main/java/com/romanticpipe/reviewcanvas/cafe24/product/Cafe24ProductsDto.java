package com.romanticpipe.reviewcanvas.cafe24.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.List;

public class Cafe24ProductsDto {
	@Getter
	private final List<Cafe24Product> products;

	@JsonCreator
	public Cafe24ProductsDto(List<Cafe24Product> products) {
		this.products = products;
	}
}
