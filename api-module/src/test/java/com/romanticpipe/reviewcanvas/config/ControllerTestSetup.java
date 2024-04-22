package com.romanticpipe.reviewcanvas.config;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public abstract class ControllerTestSetup {

	protected MockMvc mockMvc;
	protected MockMvc securityMockMvc;

	@BeforeEach
	void setUp(WebApplicationContext context) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
			.alwaysDo(MockMvcResultHandlers.print())
			.build();
		this.securityMockMvc = MockMvcBuilders.webAppContextSetup(context)
			.apply(SecurityMockMvcConfigurers.springSecurity())
			.alwaysDo(MockMvcResultHandlers.print())
			.build();
	}
}
