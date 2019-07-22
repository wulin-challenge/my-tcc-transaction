package com.wulin.boot.simple.core.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apel.gaia.commons.pager.Condition;
import org.apel.gaia.commons.pager.Order;
import org.apel.gaia.commons.pager.PageBean;
import org.apel.gaia.persist.dao.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractTccBizCommonServiceImpl<T,PK extends Serializable> implements AbstractTccBizCommonService<T,PK>{
	
	@SuppressWarnings("unchecked")
	protected Class<T> entityClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	
	//泛型注入
	@Autowired
	private CommonRepository<T, PK> repository;

	@Override
	public void store(Object... item) {
		getRepository().store(item);
	}
	
	@Override
	public void update(Object... item) {
		getRepository().update(item);
	}

	@Override
	public int executeUpdate(String qlString, Object... values) {
		return getRepository().executeUpdate(qlString, values);
	}

	@Override
	public int executeUpdate(String qlString, Map<String, Object> params) {
		return getRepository().executeUpdate(qlString, params);
	}

	@Override
	public int executeUpdate(String qlString, List<Object> values) {
		return getRepository().executeUpdate(qlString, values);
	}

	@Override
	public void doPager(PageBean pageBean, String qlString) {
		getRepository().doPager(pageBean, qlString);
	}

	@Override
	public void doPager(PageBean pageBean, String qlString, boolean cacheable) {
		getRepository().doPager(pageBean, qlString, cacheable);
	}

	@Override
	public void doPager(PageBean pageBean, String qlString, Map<String, Object> params) {
		getRepository().doPager(pageBean, qlString, params);
	}

	@Override
	public void doPager(PageBean pageBean, String qlString, List<Object> values) {
		getRepository().doPager(pageBean, qlString, values);
	}

	@Override
	public void doPager(PageBean pageBean, String qlString, Object... values) {
		getRepository().doPager(pageBean, qlString, values);
	}

	@Override
	public void doNativeSqlPager(PageBean pageBean, String qlString, List<Object> values) {
		getRepository().doNativeSqlPager(pageBean, qlString, values);
	}

	@Override
	public int batchDeleteByQl(Class<?> entityClass, Object... primaryKeyValues) {
		return getRepository().batchDeleteByQl(entityClass, primaryKeyValues);
	}

	@Override
	public int batchDeleteByQl(Class<?> entityClass, String... pKeyVals) {
		return getRepository().batchDeleteByQl(entityClass, pKeyVals);
	}

	@Override
	public List<?> doList(String qlString, List<Object> values, List<Condition> conditions, List<Order> orders,
			boolean sqlable) {
		return getRepository().doList(qlString, conditions, orders, sqlable);
	}

	@Override
	public List<?> doList(String qlString, List<Condition> conditions, List<Order> orders, boolean sqlable) {
		return getRepository().doList(qlString, conditions, orders, sqlable);
	}

	@Override
	public List<?> doList(String qlString, List<Object> values) {
		return getRepository().doList(qlString, values);
	}

	@Override
	public List<?> doList(String qlString, List<Object> values, boolean sqlable) {
		return getRepository().doList(qlString, values, sqlable);
	}
	
	/**
	 * 返回实现repository接口的实例对象
	 */
	protected CommonRepository<T, PK> getRepository(){
		return this.repository;
	}
	

	@Override
	public List<T> findAll() {
		return getRepository().findAll();
	}

	@Override
	public List<T> findAll(Iterable<Serializable> ids) {
		return getRepository().findAll(ids);
	}

	@Override
	public void flush() {
		getRepository().flush();
	}

	@Override
	public <S extends T> S saveAndFlush(S entity) {
		return getRepository().saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<T> entities) {
		getRepository().deleteInBatch(entities);
	}

	@Override
	public void deleteAllInBatch() {
		getRepository().deleteAllInBatch();
	}

	@Override
	public T getOne(Serializable id) {
		return getRepository().getOne(id);
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example) {
		return getRepository().findAll(example);
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
		return getRepository().findAll(example, sort);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return getRepository().findAll(pageable);
	}

	@Override
	public T findOne(Serializable id) {
		return getRepository().findOne(id);
	}

	@Override
	public boolean exists(Serializable id) {
		return getRepository().exists(id);
	}

	@Override
	public long count() {
		return getRepository().count();
	}

	@Override
	public void delete(Serializable id) {
		getRepository().delete(id);
	}

	@Override
	public void delete(T entity) {
		getRepository().delete(entity);
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		getRepository().delete(entities);
	}

	@Override
	public void deleteAll() {
		getRepository().deleteAll();
	}

	@Override
	public <S extends T> S findOne(Example<S> example) {
		return getRepository().findOne(example);
	}

	@Override
	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
		return getRepository().findAll(example, pageable);
	}

	@Override
	public <S extends T> long count(Example<S> example) {
		return getRepository().count(example);
	}

	@Override
	public <S extends T> boolean exists(Example<S> example) {
		return getRepository().exists(example);
	}
	
	
	/**
	 * 返回分页ql语句
	 */
	protected String getPageQl(){
		return "from " + entityClass.getSimpleName() + " where 1=1";
	}
	
	@Override
	public T findById(PK id) {
		return getRepository().findOne(id);
	}
	
	@Override
	public List<T> findAll(Sort sort){
		return getRepository().findAll(sort);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByCondition(Condition... conditions){
		return (List<T>)getRepository().doList(getPageQl(), Arrays.asList(conditions), new ArrayList<Order>(), false);
	}
	
	@Override
	public <S extends T> List<S> save(Iterable<S> entities) {
		return getRepository().save(entities);
	}
	@Override
	public <S extends T> S save(S entity) {
		return getRepository().save(entity);
	}
	
	public void update(T entity) {
		getRepository().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteById(PK... ids) {
		for (PK pk : ids) {
			getRepository().delete(pk);
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		getRepository().doPager(pageBean, getPageQl());
	}
}
