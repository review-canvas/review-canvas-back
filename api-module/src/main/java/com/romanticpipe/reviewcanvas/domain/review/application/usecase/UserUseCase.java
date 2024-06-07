package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.domain.User;

public interface UserUseCase {
	User createSaveUser(String mallId, String memberId);
}
