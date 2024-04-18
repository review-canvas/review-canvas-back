package com.romanticpipe.reviewcanvas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Product {

	@Id
	@Column(name = "product_id", columnDefinition = "BINARY(16)")
	private String id;

	@NotNull
	private Long productNo;

	@NotBlank
	@Column(name = "product_name")
	private String name;

	@NotNull
	@Column(name = "shop_admin_id")
	private Long shopAdminId;

	public Product(Long productNo, String name, Long shopAdminId) {
		this.productNo = productNo;
		this.name = name;
		this.shopAdminId = shopAdminId;
	}
}
