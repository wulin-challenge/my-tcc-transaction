package com.wulin.boot.simple.redpacket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wulin.boot.simple.core.service.AbstractTccBizCommonServiceImpl;
import com.wulin.boot.simple.redpacket.dao.TradeOrderRepository;
import com.wulin.boot.simple.redpacket.domain.TradeOrder;
import com.wulin.boot.simple.redpacket.service.TradeOrderBizService;

@Service
public class TradeOrderServiceImpl extends AbstractTccBizCommonServiceImpl<TradeOrder, Long> implements TradeOrderBizService{

	@Autowired
	private TradeOrderRepository tradeOrderRepository;

	@Override
	public TradeOrder findByMerchantOrderNo(String merchantOrderNo) {
		return tradeOrderRepository.findByMerchantOrderNo(merchantOrderNo);
	}

	@Override
	public void update(TradeOrder tradeOrder) {
		tradeOrder.updateVersion();
		super.update(tradeOrder);
	}
}
