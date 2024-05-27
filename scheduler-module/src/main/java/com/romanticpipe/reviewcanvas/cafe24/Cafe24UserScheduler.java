package com.romanticpipe.reviewcanvas.cafe24;

import com.romanticpipe.reviewcanvas.admin.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.admin.service.ShopAdminService;
import com.romanticpipe.reviewcanvas.cafe24.users.Cafe24User;
import com.romanticpipe.reviewcanvas.cafe24.users.Cafe24UserClient;
import com.romanticpipe.reviewcanvas.config.TransactionUtils;
import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class Cafe24UserScheduler {

	private final Cafe24UserClient cafe24UserClient;
	private final UserService userService;
	private final ShopAdminService shopAdminService;
	private final TransactionUtils transactionUtils;

	@SchedulerLock(name = "UpdateUser", lockAtLeastFor = "1m", lockAtMostFor = "1m")
	@Scheduled(cron = "${scheduler.update-user.cron}")
	@Scheduled(fixedRate = 1000 * 60 * 60 * 24)
	public void processUpdateUser() {
		log.info("user 정보 업데이트 scheduler 시작");
		List<ShopAdmin> shopAdmins = shopAdminService.findAll();
		shopAdmins.forEach(this::processEachShopAdmin);
		log.info("user 정보 업데이트 scheduler 종료");
	}

	private void processEachShopAdmin(ShopAdmin shopAdmin) {
		try {
			List<Cafe24User> cafe24Users = cafe24UserClient.getUsers(shopAdmin.getMallId())
				.customersprivacy()
				.stream()
				.filter(Cafe24User::isContentExist)
				.toList();
			updateUserInTransaction(cafe24Users, shopAdmin);
		} catch (Exception e) {
			log.warn("[{}] 쇼핑몰 유저 업데이트 실패. error message: {}", shopAdmin.getMallId(), e.getMessage());
		}
	}

	private void updateUserInTransaction(List<Cafe24User> cafe24Users, ShopAdmin shopAdmin) {
		transactionUtils.executeWithoutResultInWriteTransaction(transactionStatus -> {
			List<User> storedUsers = userService.findAllByMallId(shopAdmin.getMallId());
			int savedCount = cafe24Users.stream()
				.reduce(0,
					(count, cafe24User) -> count + updateOrSaveUser(cafe24User, storedUsers, shopAdmin.getMallId()),
					Integer::sum);
			log.info("[{}] 쇼핑몰 유저 업데이트 성공 - 새롭게 추가된 유저 수: {}명", shopAdmin.getMallId(), savedCount);
		});
	}

	private int updateOrSaveUser(Cafe24User cafe24User, List<User> storedUsers, String mallId) {
		Optional<User> storedUser = storedUsers.stream()
			.filter(user -> user.getMemberId().equals(cafe24User.memberId()) && user.getMallId().equals(mallId))
			.findAny();
		if (storedUser.isPresent()) {
			User user = storedUser.get();
			user.update(cafe24User.name(), cafe24User.nickName(), cafe24User.gender(),
				cafe24User.nationality(), cafe24User.birthday());
			return 0;
		} else {
			userService.save(cafe24User.toUserEntity(mallId));
			return 1;
		}
	}

}
