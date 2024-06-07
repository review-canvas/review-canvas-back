package com.romanticpipe.reviewcanvas.cafe24.users;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

public record Cafe24UserDto(@Getter Cafe24User cafe24User) {

	@JsonCreator
	public Cafe24UserDto {
	}
}
