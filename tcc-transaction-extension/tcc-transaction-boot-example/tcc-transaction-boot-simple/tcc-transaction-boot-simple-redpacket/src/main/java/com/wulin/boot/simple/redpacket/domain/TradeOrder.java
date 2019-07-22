package com.wulin.boot.simple.redpacket.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="RED_TRADE_ORDER",uniqueConstraints=@UniqueConstraint(name="MERCHANT_ORDER_NO_UNIQUE",columnNames= {"MERCHANT_ORDER_NO"}))
public class TradeOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(length=11,name="ID",nullable=false)
    private long id;

	@Column(length=11,name="SELF_USER_ID")
    private long selfUserId;

	@Column(length=11,name="OPPOSITE_USER_ID")
    private long oppositeUserId;

	@Column(length=45,name="MERCHANT_ORDER_NO")
    private String merchantOrderNo;

	@Column(length=10,name="AMOUNT")
    private BigDecimal amount;

	@Column(length=45,name="STATUS")
    private String status = "DRAFT";

	@Column(length=11,name="VERSION")
    private long version = 1l;

    public TradeOrder() {
    }

    public TradeOrder(long selfUserId, long oppositeUserId, String merchantOrderNo, BigDecimal amount) {
        this.selfUserId = selfUserId;
        this.oppositeUserId = oppositeUserId;
        this.merchantOrderNo = merchantOrderNo;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public long getSelfUserId() {
        return selfUserId;
    }

    public long getOppositeUserId() {
        return oppositeUserId;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public void confirm() {
        this.status = "CONFIRM";
    }

    public void cancel() {
        this.status = "CANCEL";
    }

    public long getVersion() {
        return version;
    }

    public void updateVersion() {
        version = version + 1;
    }

	public void setId(long id) {
		this.id = id;
	}

	public void setSelfUserId(long selfUserId) {
		this.selfUserId = selfUserId;
	}

	public void setOppositeUserId(long oppositeUserId) {
		this.oppositeUserId = oppositeUserId;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
