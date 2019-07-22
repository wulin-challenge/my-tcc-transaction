package com.wulin.boot.dubbo.capital.provider;

import java.math.BigDecimal;

import org.apel.dubbo.starter.config.SpringService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.wulin.boot.dubbo.capital.api.CapitalAccountService;
import com.wulin.boot.simple.capital.service.CapitalAccountBizService;

/**
 * Created by twinkle.zhou on 16/11/11.
 */
@SpringService
@Service(timeout=30000,interfaceClass=CapitalAccountService.class)
public class CapitalAccountProvider implements CapitalAccountService{


    @Autowired
    private CapitalAccountBizService capitalAccountBizService;

    @Override
    public BigDecimal getCapitalAccountByUserId(long userId) {
        return capitalAccountBizService.findByUserId(userId).getBalanceAmount();
    }
}
