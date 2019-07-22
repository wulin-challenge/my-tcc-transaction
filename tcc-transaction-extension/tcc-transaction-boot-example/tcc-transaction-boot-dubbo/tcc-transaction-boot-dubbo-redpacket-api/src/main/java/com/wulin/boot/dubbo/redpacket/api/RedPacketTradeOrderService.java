package com.wulin.boot.dubbo.redpacket.api;

import org.mengyun.tcctransaction.api.Compensable;

import com.wulin.boot.dubbo.redpacket.api.dto.RedPacketTradeOrderDto;

/**
 * Created by changming.xie on 4/1/16.
 */
public interface RedPacketTradeOrderService {

    @Compensable
    public String record(RedPacketTradeOrderDto tradeOrderDto);
}
