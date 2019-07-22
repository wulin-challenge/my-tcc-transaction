package com.wulin.boot.simple.capital.service;

import com.wulin.boot.simple.capital.domain.TradeOrder;
import com.wulin.boot.simple.core.service.AbstractTccBizCommonService;

public interface TradeOrderBizService extends AbstractTccBizCommonService<TradeOrder,Long>{

	public TradeOrder findByMerchantOrderNo(String merchantOrderNo);
}
