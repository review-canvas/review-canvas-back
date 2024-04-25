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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 어떤 문제를 해결하려 하는지?: cafe24에서 제공하는 샵별 product 정보를 미리 가져와 db에 저장한다.
 * 작업: 매일 새벽 3시에 cafe24에서 제공하는 product 정보를 조회하여 db에 저장하거나, 업데이트한다.
 * 시나리오(매일 새벽 3시가 되면)
 * 1. 회원가입이 완료된 shop의 shopAdminId, mallId를 가져온다.
 * 2. shopAdminId로 product id를 조회한다.
 * 3. mallId list로 product 정보를 조회한다.
 * 4. 만약 없는 product인 경우 product를 추가하고, 있는 product인 경우 업데이트한다.
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

	// TODO: 고려해야 할 사항
	// 1. product 개수가 5000개가 넘어가면 한 products 조회 api로 전부 가져올 수 없기에 분할 작업이 필요(count product api로 확인 필요)
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
				AtomicInteger savedCount = new AtomicInteger(0);
				cafe24Products.forEach(cafe24Product ->
					updateOrSaveProduct(cafe24Product, products, shopAdmin, savedCount)
				);
				log.info("{} 쇼핑몰 상품 업데이트 성공 - 새롭게 추가된 상품 수: {}", shopAdmin.getMallName(), savedCount.get());
			} catch (RuntimeException e) {
				transactionStatus.setRollbackOnly();
				log.warn("{} 쇼핑몰 상품 업데이트 실패", shopAdmin.getMallName());
				throw e;
			}
		});
	}

	private void updateOrSaveProduct(Cafe24Product cafe24Product, List<Product> existingProducts, ShopAdmin shopAdmin,
									 AtomicInteger savedCount) {
		existingProducts.stream()
			.filter(product -> cafe24Product.productNo().equals(product.getProductNo()))
			.findAny()
			.ifPresentOrElse(
				product -> product.update(cafe24Product.productName()),
				() -> {
					productCreator.save(new Product(cafe24Product.productNo(), cafe24Product.productName(),
						shopAdmin.getId()));
					savedCount.incrementAndGet();
				}
			);
	}
}
