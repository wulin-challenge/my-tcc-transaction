package com.wulin.boot.simple.redpacket.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wulin.boot.simple.common.exception.InsufficientBalanceException;

/**
 * Created by changming.xie on 4/2/16.
 */
@Entity
@Table(name="RED_RED_PACKET_ACCOUNT")
public class RedPacketAccount {

    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(length=11,name="RED_PACKET_ACCOUNT_ID",nullable=false)
    private long id;

	@Column(length=11,name="USER_ID")
    private long userId;

	@Column(length=11,name="BALANCE_AMOUNT")
    private BigDecimal balanceAmount;

    public long getUserId() {
        return userId;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void transferFrom(BigDecimal amount) {
        this.balanceAmount = this.balanceAmount.subtract(amount);

        if (BigDecimal.ZERO.compareTo(this.balanceAmount) > 0) {
            throw new InsufficientBalanceException();
        }
    }

    public void transferTo(BigDecimal amount) {
        this.balanceAmount = this.balanceAmount.add(amount);
    }

    public void cancelTransfer(BigDecimal amount) {
        transferTo(amount);
    }

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
}
