package com.romanticpipe.reviewcanvas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Product {

	@Id
	@Column(name = "product_id", columnDefinition = "binary(16)")
	private String productId;

	@Column(name = "product_name")
	private String name;

	@Column(name = "shop_admin_id")
	private long shopAdminId;
}
