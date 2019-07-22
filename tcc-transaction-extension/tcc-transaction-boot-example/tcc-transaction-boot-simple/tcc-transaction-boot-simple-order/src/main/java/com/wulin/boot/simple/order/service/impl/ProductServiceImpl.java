package com.wulin.boot.simple.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wulin.boot.simple.core.service.AbstractTccBizCommonServiceImpl;
import com.wulin.boot.simple.order.dao.ProductRepository;
import com.wulin.boot.simple.order.domain.Product;
import com.wulin.boot.simple.order.service.ProductBizService;

@Service
public class ProductServiceImpl extends AbstractTccBizCommonServiceImpl<Product, Long> implements ProductBizService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product findByProductId(long productId) {
		return productRepository.findByProductId(productId);
	}

	@Override
	public List<Product> findByShopId(long shopId) {
		return productRepository.findByShopId(shopId);
	}

}
