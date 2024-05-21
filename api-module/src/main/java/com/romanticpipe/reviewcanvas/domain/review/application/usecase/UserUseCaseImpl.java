package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.cafe24.users.Cafe24UserClient;
import com.romanticpipe.reviewcanvas.cafe24.users.Cafe24UserDto;
import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
@RequiredArgsConstructor
class UserUseCaseImpl implements UserUseCase {

	private final Cafe24UserClient cafe24UserClient;
	private final UserService userService;
	private final TransactionTemplate writeTransactionTemplate;

	@Override
	public User createSaveUser(String mallId, String memberId) {
		Cafe24UserDto cafe24User = cafe24UserClient.getUser(mallId, memberId);
		cafe24User.validateContent();

		return writeTransactionTemplate.execute(status -> {
			try {
				return userService.save(cafe24User.toUserEntity());
			} catch (RuntimeException e) {
				status.setRollbackOnly();
				throw e;
			}
		});
	}
}
