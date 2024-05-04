package com.romanticpipe.reviewcanvas.reviewproperty.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "terms_id")
	private Integer id;
	@Column(name = "terms_name")
	private String name;
	private String contents;
	private Boolean mandatory;

	@Builder
	public Terms(String name, String contents, Boolean mandatory) {
		this.name = name;
		this.contents = contents;
		this.mandatory = mandatory;
	}
}
