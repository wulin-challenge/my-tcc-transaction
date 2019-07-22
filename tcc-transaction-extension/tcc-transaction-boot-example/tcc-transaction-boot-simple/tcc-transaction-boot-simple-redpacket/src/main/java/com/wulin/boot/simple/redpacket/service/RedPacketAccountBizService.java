package com.wulin.boot.simple.redpacket.service;

import com.wulin.boot.simple.core.service.AbstractTccBizCommonService;
import com.wulin.boot.simple.redpacket.domain.RedPacketAccount;

public interface RedPacketAccountBizService extends AbstractTccBizCommonService<RedPacketAccount, Long>{
	
	public RedPacketAccount findByUserId(long userId);

}
