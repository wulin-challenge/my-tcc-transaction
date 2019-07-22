package com.wulin.boot.simple.capital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wulin.boot.simple.capital.dao.CapitalAccountRepository;
import com.wulin.boot.simple.capital.domain.CapitalAccount;
import com.wulin.boot.simple.capital.service.CapitalAccountBizService;
import com.wulin.boot.simple.core.service.AbstractTccBizCommonServiceImpl;

@Service
public class CapitalAccountServiceImpl extends AbstractTccBizCommonServiceImpl<CapitalAccount,Long> implements CapitalAccountBizService{
	
	@Autowired
	private CapitalAccountRepository capitalAccountRepository;

	@Override
	public CapitalAccount findByUserId(long userId) {
		return capitalAccountRepository.findByUserId(userId);
	}

}
