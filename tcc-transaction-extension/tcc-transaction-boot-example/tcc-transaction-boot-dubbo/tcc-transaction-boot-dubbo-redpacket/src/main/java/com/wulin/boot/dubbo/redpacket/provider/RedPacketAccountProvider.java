package com.wulin.boot.dubbo.redpacket.provider;

import java.math.BigDecimal;

import org.apel.dubbo.starter.config.SpringService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.wulin.boot.dubbo.redpacket.api.RedPacketAccountService;
import com.wulin.boot.simple.redpacket.service.RedPacketAccountBizService;

/**
 * Created by twinkle.zhou on 16/11/11.
 */
@SpringService
@Service(timeout=30000,interfaceClass=RedPacketAccountService.class)
public class RedPacketAccountProvider implements RedPacketAccountService {

    @Autowired
    RedPacketAccountBizService redPacketAccountBizService;

    @Override
    public BigDecimal getRedPacketAccountByUserId(long userId) {
        return redPacketAccountBizService.findByUserId(userId).getBalanceAmount();
    }
}
