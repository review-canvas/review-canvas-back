package com.romanticpipe.reviewcanvas.cafe24;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

import com.romanticpipe.reviewcanvas.admin.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.admin.service.ShopAdminService;
import com.romanticpipe.reviewcanvas.cafe24.product.Cafe24Product;
import com.romanticpipe.reviewcanvas.cafe24.product.Cafe24ProductClient;
import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * TASK: 매일 새벽 3시에 cafe24에서 제공하는 product 정보를 조회하여 db에 저장하거나, 업데이트한다.
 *  TODO: product 개수가 5000개가 넘어가면 한 products 조회 api로 전부 가져올 수 없기에 분할 작업이 필요(count product api로 확인 필요)
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class Cafe24ProductScheduler {

	private final ShopAdminService shopAdminService;
	private final ProductService productService;
	private final Cafe24ProductClient productClient;
	private final TransactionTemplate writeTransactionTemplate;

	@SchedulerLock(name = "UpdateProduct", lockAtLeastFor = "1m", lockAtMostFor = "1m")
	@Scheduled(cron = "${scheduler.update-product.cron}")
	public void processUpdateProduct() {
		log.info("product 정보 업데이트 scheduler 시작");
		List<ShopAdmin> shopAdmins = shopAdminService.findAll();
		shopAdmins.forEach(this::processEachShopAdmin);
		log.info("product 정보 업데이트 scheduler 종료");
	}

	private void processEachShopAdmin(ShopAdmin shopAdmin) {
		try {
			List<Cafe24Product> cafe24Products = productClient.getProducts(shopAdmin.getMallId())
				.products()
				.stream()
				.filter(Cafe24Product::isFullContent)
				.toList();
			updateProductsInTransaction(cafe24Products, shopAdmin);
		} catch (Exception e) {
			log.warn("[{}] 쇼핑몰 상품 업데이트 실패. error message: {}", shopAdmin.getMallName(), e.getMessage());
		}
	}

	private void updateProductsInTransaction(List<Cafe24Product> cafe24Products, ShopAdmin shopAdmin) {
		writeTransactionTemplate.executeWithoutResult(transactionStatus -> {
			try {
				List<Product> products = productService.findProducts(shopAdmin.getId());
				int savedCount = cafe24Products.stream()
					.reduce(0, (count, cafe24Product) ->
						count + updateOrSaveProduct(cafe24Product, products, shopAdmin), Integer::sum);
				log.info("[{}] 쇼핑몰 상품 업데이트 성공 - 새롭게 추가된 상품 수: {}", shopAdmin.getMallName(), savedCount);
			} catch (RuntimeException e) {
				transactionStatus.setRollbackOnly();
				throw e;
			}
		});
	}

	private int updateOrSaveProduct(Cafe24Product cafe24Product, List<Product> existingProducts, ShopAdmin shopAdmin) {
		return new UpdateSaveCafe24ProductTaskExecutor(productService)
			.execute(cafe24Product, existingProducts, shopAdmin);
	}

	@RequiredArgsConstructor
	static class UpdateSaveCafe24ProductTaskExecutor {
		private final ProductService productService;
		private int count = 0;

		public int execute(Cafe24Product cafe24Product, List<Product> existingProducts, ShopAdmin shopAdmin) {
			existingProducts.stream()
				.filter(product -> cafe24Product.productNo().equals(product.getProductNo()))
				.findAny()
				.ifPresentOrElse(
					product -> product.update(cafe24Product.productName()),
					() -> {
						productService.save(new Product(cafe24Product.productNo(), cafe24Product.productName(),
							shopAdmin.getId()));
						count++;
					}
				);
			return count;
		}
	}
}
