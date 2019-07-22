package com.wulin.boot.simple.redpacket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wulin.boot.simple.core.service.AbstractTccBizCommonServiceImpl;
import com.wulin.boot.simple.redpacket.dao.RedPacketAccountRepository;
import com.wulin.boot.simple.redpacket.domain.RedPacketAccount;
import com.wulin.boot.simple.redpacket.service.RedPacketAccountBizService;

@Service
public class RedPacketAccountServiceImpl extends AbstractTccBizCommonServiceImpl<RedPacketAccount, Long> implements RedPacketAccountBizService{

	@Autowired
	private RedPacketAccountRepository redPacketAccountRepository;

	@Override
	public RedPacketAccount findByUserId(long userId) {
		return redPacketAccountRepository.findByUserId(userId);
	}
	
}
