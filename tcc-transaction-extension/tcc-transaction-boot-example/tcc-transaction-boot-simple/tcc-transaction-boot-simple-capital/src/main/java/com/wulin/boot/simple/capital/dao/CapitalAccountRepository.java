package com.wulin.boot.simple.capital.dao;

import org.apel.gaia.persist.dao.CommonRepository;

import com.wulin.boot.simple.capital.domain.CapitalAccount;

public interface CapitalAccountRepository extends CommonRepository<CapitalAccount, Long>{

	public CapitalAccount findByUserId(long userId);
}
