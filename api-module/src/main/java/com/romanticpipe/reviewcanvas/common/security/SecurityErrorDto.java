package com.romanticpipe.reviewcanvas.common.security;

import java.net.URI;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SecurityErrorDto {
	private URI type;
	private String title;
	private String point;
	private int status;
	private String detail;
	private URI instance;
}
