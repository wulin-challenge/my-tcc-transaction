package com.wulin.boot.dubbo.order.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.mengyun.tcctransaction.CancellingException;
import org.mengyun.tcctransaction.ConfirmingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wulin.boot.simple.order.domain.Order;
import com.wulin.boot.simple.order.domain.Shop;
import com.wulin.boot.simple.order.service.OrderBizService;
import com.wulin.boot.simple.order.service.ShopBizService;

/**
 * Created by changming.xie on 4/1/16.
 */
@Service
public class PlaceOrderServiceImpl {

    @Autowired
    ShopBizService shopBizService;

    @Autowired
    OrderBizService orderBizService;

    @Autowired
    PaymentServiceImpl paymentService;

    public String placeOrder(long payerUserId, long shopId, List<Pair<Long, Integer>> productQuantities, BigDecimal redPacketPayAmount) {
        Shop shop = shopBizService.findById(shopId);

        Order order = orderBizService.createOrder(payerUserId, shop.getOwnerUserId(), productQuantities);

        Boolean result = false;

        try {

            paymentService.makePayment(order, redPacketPayAmount, order.getTotalAmount().subtract(redPacketPayAmount));

        } catch (ConfirmingException confirmingException) {
            //exception throws with the tcc transaction status is CONFIRMING,
            //when tcc transaction is confirming status,
            // the tcc transaction recovery will try to confirm the whole transaction to ensure eventually consistent.

            result = true;
        } catch (CancellingException cancellingException) {
            //exception throws with the tcc transaction status is CANCELLING,
            //when tcc transaction is under CANCELLING status,
            // the tcc transaction recovery will try to cancel the whole transaction to ensure eventually consistent.
        } catch (Throwable e) {
            //other exceptions throws at TRYING stage.
            //you can retry or cancel the operation.
            e.printStackTrace();
        }

        return order.getMerchantOrderNo();
    }
}
