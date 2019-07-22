package com.wulin.boot.simple.capital.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;

import com.wulin.boot.simple.common.exception.InsufficientBalanceException;

/**
 * Created by changming.xie on 4/2/16.
 */
@Entity
@Table(name="CAP_CAPITAL_ACCOUNT")
public class CapitalAccount {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(length=11,name="CAPITAL_ACCOUNT_ID",nullable=false)
    private long id;

	@Column(length=11,name="USER_ID")
    private long userId;

	@Column(length=11,name="BALANCE_AMOUNT")
    private BigDecimal balanceAmount;

	@Transient
    private BigDecimal transferAmount = BigDecimal.ZERO;
    
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

        transferAmount = transferAmount.add(amount.negate());
    }

    public void transferTo(BigDecimal amount) {
        this.balanceAmount = this.balanceAmount.add(amount);
        transferAmount = transferAmount.add(amount);
    }

    public void cancelTransfer(BigDecimal amount) {
        transferTo(amount);
    }

	public BigDecimal getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(BigDecimal transferAmount) {
		if(transferAmount == null) {
			transferAmount = BigDecimal.ZERO;
		}
		this.transferAmount = transferAmount;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
    
}
