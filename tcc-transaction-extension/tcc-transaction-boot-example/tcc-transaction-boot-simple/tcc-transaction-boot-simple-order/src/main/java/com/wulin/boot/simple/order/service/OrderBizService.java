package com.wulin.boot.simple.order.service;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.wulin.boot.simple.core.service.AbstractTccBizCommonService;
import com.wulin.boot.simple.order.domain.Order;

public interface OrderBizService extends AbstractTccBizCommonService<Order, Long>{

	 public void createOrder(Order order);
	 
	 public Order createOrder(long payerUserId, long payeeUserId, List<Pair<Long, Integer>> productQuantities);
	 
	 public void updateOrder(Order order);
	 
	 public Order findByMerchantOrderNo(String merchantOrderNo);
}
