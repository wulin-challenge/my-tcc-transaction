package com.wulin.boot.simple.order.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by changming.xie on 4/1/16.
 */
@Entity
@Table(name="ORD_ORDER_LINE")
public class OrderLine implements Serializable {

    private static final long serialVersionUID = 2300754647209250837L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(length=11,name="ORDER_LINE_ID",nullable=false)
    private long id;

    @Column(length=11,name="PRODUCT_ID")
    private long productId;

    @Column(length=10,name="QUANTITY")
    private int quantity;

    @Column(length=10,name="UNIT_PRICE")
    private BigDecimal unitPrice;
    
    public OrderLine() {

    }

    public OrderLine(Long productId, Integer quantity,BigDecimal unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotalAmount() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public long getId() {
        return id;
    }

	public void setId(long id) {
		this.id = id;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
}
