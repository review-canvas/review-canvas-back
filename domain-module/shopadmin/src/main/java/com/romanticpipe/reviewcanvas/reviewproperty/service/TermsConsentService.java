package com.romanticpipe.reviewcanvas.reviewproperty.service;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.TermsConsent;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.TermsConsentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermsConsentService {
	private final TermsConsentRepository termsConsentRepository;

	public void createAll(List<Integer> termsIds, Integer shopAdminId) {
		termsIds.forEach(termsId -> {
			TermsConsent termsConsent = TermsConsent.builder()
				.termsId(termsId)
				.shopAdminId(shopAdminId)
				.build();
			termsConsentRepository.save(termsConsent);
		});
	}
}
