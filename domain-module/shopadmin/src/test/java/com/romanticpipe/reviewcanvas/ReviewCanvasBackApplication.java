package com.romanticpipe.reviewcanvas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ReviewCanvasBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewCanvasBackApplication.class, args);
	}

}
