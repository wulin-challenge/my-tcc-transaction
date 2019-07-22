package com.wulin.boot.simple.order.domain;

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
@Table(name = "ORD_SHOP")
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 11, name = "SHOP_ID", nullable = false)
	private long id;

	@Column(length=11,name="OWNER_USER_ID")
	private long ownerUserId;

	public long getOwnerUserId() {
		return ownerUserId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setOwnerUserId(long ownerUserId) {
		this.ownerUserId = ownerUserId;
	}
}
