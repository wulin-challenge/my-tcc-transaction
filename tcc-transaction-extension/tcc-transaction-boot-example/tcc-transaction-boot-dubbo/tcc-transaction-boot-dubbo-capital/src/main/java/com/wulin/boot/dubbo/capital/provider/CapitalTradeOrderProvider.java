package com.wulin.boot.dubbo.capital.provider;

import java.util.Calendar;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apel.dubbo.starter.config.SpringService;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.dubbo.context.DubboTransactionContextEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.wulin.boot.dubbo.capital.api.CapitalTradeOrderService;
import com.wulin.boot.dubbo.capital.api.dto.CapitalTradeOrderDto;
import com.wulin.boot.simple.capital.domain.CapitalAccount;
import com.wulin.boot.simple.capital.domain.TradeOrder;
import com.wulin.boot.simple.capital.service.CapitalAccountBizService;
import com.wulin.boot.simple.capital.service.TradeOrderBizService;

/**
 * Created by changming.xie on 4/2/16.
 */
@SpringService
@Service(timeout=30000,interfaceClass=CapitalTradeOrderService.class)
public class CapitalTradeOrderProvider implements CapitalTradeOrderService {

    @Autowired
    private CapitalAccountBizService capitalAccountBizService;

    @Autowired
    private TradeOrderBizService tradeOrderBizService;

    @Override
    @Compensable(confirmMethod = "confirmRecord", cancelMethod = "cancelRecord", transactionContextEditor = DubboTransactionContextEditor.class)
    @Transactional
    public String record(CapitalTradeOrderDto tradeOrderDto) {

        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("capital try record called. time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));


        TradeOrder foundTradeOrder = tradeOrderBizService.findByMerchantOrderNo(tradeOrderDto.getMerchantOrderNo());


        //check if trade order has been recorded, if yes, return success directly.
        if (foundTradeOrder == null) {

            TradeOrder tradeOrder = new TradeOrder(
                    tradeOrderDto.getSelfUserId(),
                    tradeOrderDto.getOppositeUserId(),
                    tradeOrderDto.getMerchantOrderNo(),
                    tradeOrderDto.getAmount()
            );

            try {
            	tradeOrderBizService.save(tradeOrder);

                CapitalAccount transferFromAccount = capitalAccountBizService.findByUserId(tradeOrderDto.getSelfUserId());

                transferFromAccount.transferFrom(tradeOrderDto.getAmount());

                capitalAccountBizService.save(transferFromAccount);

            } catch (DataIntegrityViolationException e) {
                //this exception may happen when insert trade order concurrently, if happened, ignore this insert operation.
            }
        }

        return "success";
    }

    @Transactional
    public void confirmRecord(CapitalTradeOrderDto tradeOrderDto) {
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("capital confirm record called. time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));

        TradeOrder tradeOrder = tradeOrderBizService.findByMerchantOrderNo(tradeOrderDto.getMerchantOrderNo());

        //check if the trade order status is DRAFT, if yes, return directly, ensure idempotency.
        if (tradeOrder != null && tradeOrder.getStatus().equals("DRAFT")) {
            tradeOrder.confirm();
            tradeOrderBizService.update(tradeOrder);

            CapitalAccount transferToAccount = capitalAccountBizService.findByUserId(tradeOrderDto.getOppositeUserId());

            transferToAccount.transferTo(tradeOrderDto.getAmount());

            capitalAccountBizService.save(transferToAccount);
        }
    }

	@Transactional
    public void cancelRecord(CapitalTradeOrderDto tradeOrderDto) {
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("capital cancel record called. time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));

        TradeOrder tradeOrder = tradeOrderBizService.findByMerchantOrderNo(tradeOrderDto.getMerchantOrderNo());

        //check if the trade order status is DRAFT, if yes, return directly, ensure idempotency.
        if (null != tradeOrder && "DRAFT".equals(tradeOrder.getStatus())) {
            tradeOrder.cancel();
            tradeOrderBizService.update(tradeOrder);

            CapitalAccount capitalAccount = capitalAccountBizService.findByUserId(tradeOrderDto.getSelfUserId());

            capitalAccount.cancelTransfer(tradeOrderDto.getAmount());

            capitalAccountBizService.save(capitalAccount);
        }
    }
}
