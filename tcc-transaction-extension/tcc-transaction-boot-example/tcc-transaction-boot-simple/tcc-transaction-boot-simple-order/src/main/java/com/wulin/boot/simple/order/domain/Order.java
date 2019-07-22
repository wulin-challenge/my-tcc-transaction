package com.wulin.boot.simple.order.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * Created by changming.xie on 3/25/16.
 */
@Entity
@Table(name="ORD_ORDER",uniqueConstraints=@UniqueConstraint(name="MERCHANT_ORD_ORDER_NO_UNIQUE",columnNames= {"MERCHANT_ORDER_NO"}))
public class Order implements Serializable {

    private static final long serialVersionUID = -5908730245224893590L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(length=11,name="ORDER_ID",nullable=false)
    private long id;

    @Column(length=11,name="PAYER_USER_ID")
    private long payerUserId;

    @Column(length=11,name="PAYEE_USER_ID")
    private long payeeUserId;

    @Column(length=10,name="RED_PACKET_PAY_AMOUNT")
    private BigDecimal redPacketPayAmount;

    @Column(length=11,name="CAPITAL_PAY_AMOUNT")
    private BigDecimal capitalPayAmount;

    @Column(length=45,name="STATUS")
    private String status = "DRAFT";

    @Column(length=45,name="MERCHANT_ORDER_NO")
    private String merchantOrderNo;

    @Column(length=11,name="VERSION")
    private long version = 1l;

    @Transient
    private List<OrderLine> orderLines = new ArrayList<OrderLine>();
    
    public Order() {

    }

    public Order(long payerUserId, long payeeUserId) {
        this.payerUserId = payerUserId;
        this.payeeUserId = payeeUserId;
        this.merchantOrderNo = UUID.randomUUID().toString();
    }

    public long getPayerUserId() {
        return payerUserId;
    }

    public long getPayeeUserId() {
        return payeeUserId;
    }

    public BigDecimal getTotalAmount() {

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderLine orderLine : orderLines) {

            totalAmount = totalAmount.add(orderLine.getTotalAmount());
        }
        return totalAmount;
    }

    public void addOrderLine(OrderLine orderLine) {
        this.orderLines.add(orderLine);
    }

    public List<OrderLine> getOrderLines() {
        return Collections.unmodifiableList(this.orderLines);
    }

    public void pay(BigDecimal redPacketPayAmount, BigDecimal capitalPayAmount) {
        this.redPacketPayAmount = redPacketPayAmount;
        this.capitalPayAmount = capitalPayAmount;
        this.status = "PAYING";
    }

    public BigDecimal getRedPacketPayAmount() {
        return redPacketPayAmount;
    }

    public BigDecimal getCapitalPayAmount() {
        return capitalPayAmount;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void confirm() {
        this.status = "CONFIRMED";
    }

    public void cancelPayment() {
        this.status = "PAY_FAILED";
    }


    public long getVersion() {
        return version;
    }

    public void updateVersion() {
        version++;
    }

	public void setId(long id) {
		this.id = id;
	}

	public void setPayerUserId(long payerUserId) {
		this.payerUserId = payerUserId;
	}

	public void setPayeeUserId(long payeeUserId) {
		this.payeeUserId = payeeUserId;
	}

	public void setRedPacketPayAmount(BigDecimal redPacketPayAmount) {
		this.redPacketPayAmount = redPacketPayAmount;
	}

	public void setCapitalPayAmount(BigDecimal capitalPayAmount) {
		this.capitalPayAmount = capitalPayAmount;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
}
