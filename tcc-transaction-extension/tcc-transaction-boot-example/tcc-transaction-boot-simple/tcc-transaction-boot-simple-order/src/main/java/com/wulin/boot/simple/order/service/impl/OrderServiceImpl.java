package com.wulin.boot.simple.order.service.impl;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wulin.boot.simple.core.service.AbstractTccBizCommonServiceImpl;
import com.wulin.boot.simple.order.dao.OrderLineRepository;
import com.wulin.boot.simple.order.dao.OrderRepository;
import com.wulin.boot.simple.order.domain.Order;
import com.wulin.boot.simple.order.domain.OrderLine;
import com.wulin.boot.simple.order.factory.OrderFactory;
import com.wulin.boot.simple.order.service.OrderBizService;

@Service
public class OrderServiceImpl extends AbstractTccBizCommonServiceImpl<Order, Long> implements OrderBizService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderLineRepository orderLineRepository;
	
	@Autowired
	private OrderFactory orderFactory;

	@Override
	public void createOrder(Order order) {
		orderRepository.save(order);
		
		for (OrderLine orderLine : order.getOrderLines()) {
			orderLineRepository.save(orderLine);
		}
	}
	
	@Override
	public Order createOrder(long payerUserId, long payeeUserId, List<Pair<Long, Integer>> productQuantities) {
		Order order = orderFactory.buildOrder(payerUserId, payeeUserId, productQuantities);

        createOrder(order);
        return order;
	}



	@Override
	public void updateOrder(Order order) {
		order.updateVersion();
		
		orderRepository.update(order);
	}

	@Override
	public Order findByMerchantOrderNo(String merchantOrderNo) {
		return orderRepository.findByMerchantOrderNo(merchantOrderNo);
	}
}
