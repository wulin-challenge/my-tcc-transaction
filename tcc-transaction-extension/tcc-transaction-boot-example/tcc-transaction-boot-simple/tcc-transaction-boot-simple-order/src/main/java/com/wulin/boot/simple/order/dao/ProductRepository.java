package com.wulin.boot.simple.order.dao;

import java.util.List;

import org.apel.gaia.persist.dao.CommonRepository;

import com.wulin.boot.simple.order.domain.Product;

public interface ProductRepository extends CommonRepository<Product, Long>{

	public Product findByProductId(long productId);
	
	public List<Product> findByShopId(long shopId);
}
