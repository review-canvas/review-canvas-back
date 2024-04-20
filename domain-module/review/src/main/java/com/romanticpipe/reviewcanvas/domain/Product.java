package com.romanticpipe.reviewcanvas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;

	private Long productNo;
	@Column(name = "product_name")
	private String name;
	private Integer shopAdminId;

	public Product(Long productNo, String name, Integer shopAdminId) {
		this.productNo = productNo;
		this.name = name;
		this.shopAdminId = shopAdminId;
	}
}
