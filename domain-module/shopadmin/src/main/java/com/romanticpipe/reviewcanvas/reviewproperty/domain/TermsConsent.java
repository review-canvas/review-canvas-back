package com.romanticpipe.reviewcanvas.reviewproperty.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@EntityListeners(AuditingEntityListener.class)
public class TermsConsent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "terms_consent_id")
	private Integer id;
	private Integer termsId;
	private Boolean consent;

	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime consentAt;
	private Integer shopAdminId;

	@Builder
	public TermsConsent(Integer termsId, Boolean consent, Integer shopAdminId) {
		this.termsId = termsId;
		this.consent = consent;
		this.shopAdminId = shopAdminId;
	}
}
