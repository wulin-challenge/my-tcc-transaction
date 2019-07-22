package com.wulin.boot.simple.order.dao;

import org.apel.gaia.persist.dao.CommonRepository;
import org.springframework.data.jpa.repository.Query;

import com.wulin.boot.simple.order.domain.Order;

public interface OrderRepository extends CommonRepository<Order, Long>{

	@Query("select o from Order o where o.merchantOrderNo = ?1")
	public Order findByMerchantOrderNo(String merchantOrderNo);
}
