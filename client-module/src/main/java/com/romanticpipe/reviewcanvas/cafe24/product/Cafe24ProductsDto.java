package com.romanticpipe.reviewcanvas.cafe24.product;

import lombok.Getter;

import java.util.List;

public record Cafe24ProductsDto(@Getter List<Cafe24Product> products) {
}
