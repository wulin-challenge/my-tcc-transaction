package com.wulin.boot.simple.order.dao;

import org.apel.gaia.persist.dao.CommonRepository;

import com.wulin.boot.simple.order.domain.Shop;

public interface ShopRepository extends CommonRepository<Shop, Long>{

	 public Shop findById(long id);
}
