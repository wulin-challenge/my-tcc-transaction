package com.wulin.boot.simple.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wulin.boot.simple.core.service.AbstractTccBizCommonServiceImpl;
import com.wulin.boot.simple.order.dao.ShopRepository;
import com.wulin.boot.simple.order.domain.Shop;
import com.wulin.boot.simple.order.service.ShopBizService;

@Service
public class ShopSeriviceImpl extends AbstractTccBizCommonServiceImpl<Shop, Long> implements ShopBizService{

	@Autowired
	private ShopRepository shopRepository;
	
	@Override
	public Shop findById(long id) {
		return shopRepository.findById(id);
	}

}
