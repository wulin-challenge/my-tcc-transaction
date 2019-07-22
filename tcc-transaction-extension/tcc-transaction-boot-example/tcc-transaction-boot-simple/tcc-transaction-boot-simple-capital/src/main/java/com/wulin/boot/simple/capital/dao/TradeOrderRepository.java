package com.wulin.boot.simple.capital.dao;

import org.apel.gaia.persist.dao.CommonRepository;
import org.springframework.data.jpa.repository.Query;

import com.wulin.boot.simple.capital.domain.TradeOrder;

public interface TradeOrderRepository extends CommonRepository<TradeOrder, Long>{

	@Query("select o from TradeOrder o where o.merchantOrderNo = ?1")
	public TradeOrder findByMerchantOrderNo(String merchantOrderNo);
}
