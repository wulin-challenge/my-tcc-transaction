package com.wulin.boot.simple.capital.service;

import com.wulin.boot.simple.capital.domain.CapitalAccount;
import com.wulin.boot.simple.core.service.AbstractTccBizCommonService;

public interface CapitalAccountBizService extends AbstractTccBizCommonService<CapitalAccount,Long>{

	public CapitalAccount findByUserId(long userId);
}
