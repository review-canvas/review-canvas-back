package com.romanticpipe.reviewcanvas.enumeration;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReviewPeriod(LocalDateTime startDate, LocalDateTime endDate) {

	public static ReviewPeriod of(String period) {
		LocalDate today = LocalDate.now();
		return switch (period) {
			case "ALL" -> null;
			case "TODAY" -> new ReviewPeriod(today.atStartOfDay(), today.atStartOfDay().plusDays(1));
			case "ONE_MONTH" -> new ReviewPeriod(today.minusMonths(1).atStartOfDay(), today.atStartOfDay());
			case "THREE_MONTH" -> new ReviewPeriod(today.minusMonths(3).atStartOfDay(), today.atStartOfDay());
			case "SIX_MONTH" -> new ReviewPeriod(today.minusMonths(6).atStartOfDay(), today.atStartOfDay());
			default -> {
				String[] periods = period.split("~");
				if (periods.length != 2) {
					throw new IllegalArgumentException("Invalid period: " + period);
				}

				try {
					yield new ReviewPeriod(LocalDate.parse(periods[0]).atStartOfDay(),
						LocalDate.parse(periods[1]).atStartOfDay().plusDays(1));
				} catch (Exception e) {
					throw new IllegalArgumentException("Invalid period: " + period);
				}
			}
		};
	}

}
