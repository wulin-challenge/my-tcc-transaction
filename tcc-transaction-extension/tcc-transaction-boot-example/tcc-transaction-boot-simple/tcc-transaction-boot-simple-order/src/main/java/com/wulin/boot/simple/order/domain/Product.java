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
 * Created by twinkle.zhou on 16/11/10.
 */

@Entity
@Table(name="ORD_PRODUCT")
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(length=11,name="PRODUCT_ID",nullable=false)
    private long productId;

	@Column(length=11,name="SHOP_ID",nullable=false)
    private long shopId;

	@Column(length=64,name="PRODUCT_NAME")
    private String productName;

	@Column(length=10,name="PRICE")
    private BigDecimal price;

    public Product() {
    }

    public Product(long productId, long shopId, String productName, BigDecimal price) {
        this.productId = productId;
        this.shopId = shopId;
        this.productName = productName;
        this.price = price;
    }

    public long getProductId() {
        return productId;
    }

    public long getShopId() {
        return shopId;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
