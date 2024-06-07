package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.cafe24.users.Cafe24User;
import com.romanticpipe.reviewcanvas.cafe24.users.Cafe24UserClient;
import com.romanticpipe.reviewcanvas.config.TransactionUtils;
import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserUseCaseImpl implements UserUseCase {

	private final Cafe24UserClient cafe24UserClient;
	private final UserService userService;
	private final TransactionUtils transactionUtils;

	@Override
	public User createSaveUser(String mallId, String memberId) {
		Cafe24User cafe24User = cafe24UserClient.getUser(mallId, memberId).cafe24User();
		cafe24User.validateContent();

		return transactionUtils.executeInWriteTransaction(
			status -> userService.save(cafe24User.toUserEntity(mallId))
		);
	}
}
