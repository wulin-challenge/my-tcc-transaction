package com.wulin.boot.simple.order.service.impl;

import org.springframework.stereotype.Service;

import com.wulin.boot.simple.core.service.AbstractTccBizCommonServiceImpl;
import com.wulin.boot.simple.order.domain.OrderLine;
import com.wulin.boot.simple.order.service.OrderLineBizService;

@Service
public class OrderLineServiceImpl extends AbstractTccBizCommonServiceImpl<OrderLine, Long> implements OrderLineBizService{

}
