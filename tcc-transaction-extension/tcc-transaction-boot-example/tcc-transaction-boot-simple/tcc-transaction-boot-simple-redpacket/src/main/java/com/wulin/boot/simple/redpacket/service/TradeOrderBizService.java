package com.wulin.boot.simple.redpacket.service;

import com.wulin.boot.simple.core.service.AbstractTccBizCommonService;
import com.wulin.boot.simple.redpacket.domain.TradeOrder;

public interface TradeOrderBizService extends AbstractTccBizCommonService<TradeOrder, Long>{

	public TradeOrder findByMerchantOrderNo(String merchantOrderNo);
}
