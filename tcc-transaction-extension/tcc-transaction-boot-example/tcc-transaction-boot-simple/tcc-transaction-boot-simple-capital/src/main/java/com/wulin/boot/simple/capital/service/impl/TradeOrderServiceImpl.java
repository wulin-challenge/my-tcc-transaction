package com.wulin.boot.simple.capital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wulin.boot.simple.capital.dao.TradeOrderRepository;
import com.wulin.boot.simple.capital.domain.TradeOrder;
import com.wulin.boot.simple.capital.service.TradeOrderBizService;
import com.wulin.boot.simple.core.service.AbstractTccBizCommonServiceImpl;

@Service
public class TradeOrderServiceImpl  extends AbstractTccBizCommonServiceImpl<TradeOrder,Long> implements TradeOrderBizService{
	
	@Autowired
	private TradeOrderRepository tradeOrderRepository;

	@Override
	public TradeOrder findByMerchantOrderNo(String merchantOrderNo) {
		return tradeOrderRepository.findByMerchantOrderNo(merchantOrderNo);
	}
}
