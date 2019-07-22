package com.wulin.boot.dubbo.capital.test;

import java.math.BigDecimal;

import org.apel.gaia.app.boot.AppStarter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wulin.boot.simple.capital.domain.CapitalAccount;
import com.wulin.boot.simple.capital.service.CapitalAccountBizService;

@SpringBootTest(classes=AppStarter.class)
@RunWith(SpringRunner.class)
public class CapitalAccountServiceTest {
	
	@Autowired
	private CapitalAccountBizService capitalAccountBizService;

	@Test
	public void saveTest() {
		save();
	}
	
	private void save() {
		CapitalAccount capitalAccount = new CapitalAccount();
		capitalAccount.setBalanceAmount(new BigDecimal(200000000000L));
		capitalAccount.setTransferAmount(new BigDecimal(10L));
		capitalAccount.setUserId(1);
		
		capitalAccountBizService.save(capitalAccount);
		
		System.out.println();
		System.out.println();
	}
}
