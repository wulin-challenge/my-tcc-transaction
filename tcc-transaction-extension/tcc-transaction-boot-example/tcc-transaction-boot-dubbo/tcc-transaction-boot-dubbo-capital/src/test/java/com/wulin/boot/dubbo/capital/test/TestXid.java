package com.wulin.boot.dubbo.capital.test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.mengyun.tcctransaction.Transaction;
import org.mengyun.tcctransaction.repository.JdbcTransactionRepository;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 测试xid
 * @author ThinkPad
 *
 */
public class TestXid {
	
	@Test
	public void findXid() {
		JdbcTransactionRepository repository = new JdbcTransactionRepository();
		repository.setDomain("tcc");
		repository.setDataSource(getDataSource());
		repository.setExpireDuration(6000);
//		repository.setTbSuffix("_CAP");
		repository.setTbSuffix("_ORD");
		
		List<Transaction> findAllUnmodifiedSince = repository.findAllUnmodifiedSince(new Date());
		for (Transaction transaction : findAllUnmodifiedSince) {
			System.out.println(transaction.getXid());
		}
		System.out.println(findAllUnmodifiedSince);
	}
	
	private DataSource getDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl("jdbc:mysql://zxq:3306/boot_tcc?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	

}
