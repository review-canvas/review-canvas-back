package com.romanticpipe.reviewcanvas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Product {

	@Id
	@Column(name = "product_id")
	private String productId;

	@Column(name = "product_name")
	private String productName;
}
