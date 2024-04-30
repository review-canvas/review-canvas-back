package com.romanticpipe.reviewcanvas.reviewproperty.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.TermsConsent;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.TermsConsentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TermsConsentService {
	private final TermsConsentRepository termsConsentRepository;

	public TermsConsent save(TermsConsent termsConsent) {
		return termsConsentRepository.save(termsConsent);
	}
}
