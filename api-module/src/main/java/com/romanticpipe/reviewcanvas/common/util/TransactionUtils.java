package com.romanticpipe.reviewcanvas.common.util;

import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.CommonErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransactionUtils {

	private final TransactionTemplate readTransactionTemplate;
	private final TransactionTemplate writeTransactionTemplate;

	public <T> T executeInReadTransaction(TransactionCallback<T> action) {
		return Optional.ofNullable(readTransactionTemplate.execute(status -> {
			try {
				return action.doInTransaction(status);
			} catch (RuntimeException e) {
				status.setRollbackOnly();
				throw e;
			}
		})).orElseThrow(() -> new BusinessException(CommonErrorCode.INTERNAL_SERVER_ERROR));
	}

	public <T> T executeInWriteTransaction(TransactionCallback<T> action) {
		return Optional.ofNullable(writeTransactionTemplate.execute(status -> {
			try {
				return action.doInTransaction(status);
			} catch (RuntimeException e) {
				status.setRollbackOnly();
				throw e;
			}
		})).orElseThrow(() -> new BusinessException(CommonErrorCode.INTERNAL_SERVER_ERROR));
	}

	public void executeWithoutResultInReadTransaction(TransactionCallback<Void> action) {
		readTransactionTemplate.executeWithoutResult(status -> {
			try {
				action.doInTransaction(status);
			} catch (RuntimeException e) {
				status.setRollbackOnly();
				throw e;
			}
		});
	}

	public void executeWithoutResultInWriteTransaction(TransactionCallback<Void> action) {
		writeTransactionTemplate.executeWithoutResult(status -> {
			try {
				action.doInTransaction(status);
			} catch (RuntimeException e) {
				status.setRollbackOnly();
				throw e;
			}
		});
	}

	@FunctionalInterface
	public interface TransactionCallback<T> {
		T doInTransaction(TransactionStatus status) throws RuntimeException;
	}
}

