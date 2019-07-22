package com.wulin.boot.simple.order.service;

import java.util.List;

import com.wulin.boot.simple.core.service.AbstractTccBizCommonService;
import com.wulin.boot.simple.order.domain.Product;

public interface ProductBizService extends AbstractTccBizCommonService<Product, Long> {

	public Product findByProductId(long productId);

	public List<Product> findByShopId(long shopId);
}
