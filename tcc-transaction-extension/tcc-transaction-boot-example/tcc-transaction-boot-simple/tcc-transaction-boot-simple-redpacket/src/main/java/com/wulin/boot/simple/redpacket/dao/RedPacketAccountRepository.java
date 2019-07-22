package com.wulin.boot.simple.redpacket.dao;

import org.apel.gaia.persist.dao.CommonRepository;

import com.wulin.boot.simple.redpacket.domain.RedPacketAccount;

public interface RedPacketAccountRepository extends CommonRepository<RedPacketAccount, Long>{

	public RedPacketAccount findByUserId(long userId);
}
