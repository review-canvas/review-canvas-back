package com.romanticpipe.reviewcanvas.cafe24;

import com.romanticpipe.reviewcanvas.cafe24.product.Cafe24Product;
import com.romanticpipe.reviewcanvas.cafe24.product.Cafe24ProductClient;
import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.service.ProductCreator;
import com.romanticpipe.reviewcanvas.service.ProductReader;
import com.romanticpipe.reviewcanvas.service.ShopAdminReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * TASK: 매일 새벽 3시에 cafe24에서 제공하는 product 정보를 조회하여 db에 저장하거나, 업데이트한다.
 *  TODO: product 개수가 5000개가 넘어가면 한 products 조회 api로 전부 가져올 수 없기에 분할 작업이 필요(count product api로 확인 필요)
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class Cafe24ProductScheduler {

	private final ShopAdminReader shopAdminReader;
	private final ProductReader productReader;
	private final ProductCreator productCreator;
	private final Cafe24ProductClient productClient;
	private final TransactionTemplate writeTransactionTemplate;

	@SchedulerLock(name = "UpdateProduct", lockAtLeastFor = "1m", lockAtMostFor = "1m")
	@Scheduled(cron = "${scheduler.update-product.cron}")
	public void processUpdateProduct() {
		log.info("product 정보 업데이트 scheduler 시작");
		List<ShopAdmin> shopAdmins = shopAdminReader.findRegisteredShopAdmin();
		shopAdmins.forEach(this::processEachShopAdmin);
		log.info("product 정보 업데이트 scheduler 종료");
	}

	private void processEachShopAdmin(ShopAdmin shopAdmin) {
		List<Cafe24Product> cafe24Products = productClient.getProducts(shopAdmin.getMallId())
			.products()
			.stream()
			.filter(Cafe24Product::isFullContent)
			.toList();
		updateProductsInTransaction(cafe24Products, shopAdmin);
	}

	private void updateProductsInTransaction(List<Cafe24Product> cafe24Products, ShopAdmin shopAdmin) {
		writeTransactionTemplate.executeWithoutResult(transactionStatus -> {
			try {
				List<Product> products = productReader.findByShopAdminId(shopAdmin.getId());
				int savedCount = cafe24Products.stream()
					.reduce(0, (count, cafe24Product) ->
						count + updateOrSaveProduct(cafe24Product, products, shopAdmin), Integer::sum);
				log.info("{} 쇼핑몰 상품 업데이트 성공 - 새롭게 추가된 상품 수: {}", shopAdmin.getMallName(), savedCount);
			} catch (RuntimeException e) {
				transactionStatus.setRollbackOnly();
				log.warn("{} 쇼핑몰 상품 업데이트 실패", shopAdmin.getMallName());
				throw e;
			}
		});
	}

	private int updateOrSaveProduct(Cafe24Product cafe24Product, List<Product> existingProducts, ShopAdmin shopAdmin) {
		return new UpdateSaveCafe24ProductTaskExecutor(productCreator)
			.execute(cafe24Product, existingProducts, shopAdmin);
	}

	@RequiredArgsConstructor
	static class UpdateSaveCafe24ProductTaskExecutor {
		private final ProductCreator productCreator;
		private int count = 0;

		public int execute(Cafe24Product cafe24Product, List<Product> existingProducts, ShopAdmin shopAdmin) {
			existingProducts.stream()
				.filter(product -> cafe24Product.productNo().equals(product.getProductNo()))
				.findAny()
				.ifPresentOrElse(
					product -> product.update(cafe24Product.productName()),
					() -> {
						productCreator.save(new Product(cafe24Product.productNo(), cafe24Product.productName(),
							shopAdmin.getId()));
						count++;
					}
				);
			return count;
		}
	}
}
