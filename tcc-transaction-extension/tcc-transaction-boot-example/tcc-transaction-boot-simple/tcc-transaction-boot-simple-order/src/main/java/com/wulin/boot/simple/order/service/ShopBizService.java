package com.wulin.boot.simple.order.service;

import com.wulin.boot.simple.core.service.AbstractTccBizCommonService;
import com.wulin.boot.simple.order.domain.Shop;

public interface ShopBizService extends AbstractTccBizCommonService<Shop, Long>{

	 public Shop findById(long id);
}
