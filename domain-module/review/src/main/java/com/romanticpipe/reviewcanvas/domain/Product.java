package com.romanticpipe.reviewcanvas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product {

	@Id
	@Column(name = "product_id", columnDefinition = "BINARY(16)")
	private String id;

	@Column(name = "product_name")
	private String name;

	@Column(name = "shop_admin_id")
	private long shopAdminId;

	public Product(String productId, String name, long shopAdminId) {
		this.id = productId;
		this.name = name;
		this.shopAdminId = shopAdminId;
	}
}
