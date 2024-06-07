package com.romanticpipe.reviewcanvas.config;

import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.CommonErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class TransactionUtils {

	private final TransactionTemplate readTransactionTemplate;
	private final TransactionTemplate writeTransactionTemplate;

	public <T> T executeInReadTransaction(Function<TransactionStatus, T> action) {
		return Optional.ofNullable(readTransactionTemplate.execute(status -> {
			try {
				return action.apply(status);
			} catch (RuntimeException e) {
				status.setRollbackOnly();
				throw e;
			}
		})).orElseThrow(() -> new BusinessException(CommonErrorCode.INTERNAL_SERVER_ERROR));
	}

	public <T> T executeInWriteTransaction(Function<TransactionStatus, T> action) {
		return Optional.ofNullable(writeTransactionTemplate.execute(status -> {
			try {
				return action.apply(status);
			} catch (RuntimeException e) {
				status.setRollbackOnly();
				throw e;
			}
		})).orElseThrow(() -> new BusinessException(CommonErrorCode.INTERNAL_SERVER_ERROR));
	}

	public void executeWithoutResultInReadTransaction(Consumer<TransactionStatus> action) {
		readTransactionTemplate.executeWithoutResult(status -> {
			try {
				action.accept(status);
			} catch (RuntimeException e) {
				status.setRollbackOnly();
				throw e;
			}
		});
	}

	public void executeWithoutResultInWriteTransaction(Consumer<TransactionStatus> action) {
		writeTransactionTemplate.executeWithoutResult(status -> {
			try {
				action.accept(status);
			} catch (RuntimeException e) {
				status.setRollbackOnly();
				throw e;
			}
		});
	}
}

