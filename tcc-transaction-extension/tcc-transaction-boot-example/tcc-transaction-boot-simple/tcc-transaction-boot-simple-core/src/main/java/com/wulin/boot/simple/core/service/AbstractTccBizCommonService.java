package com.wulin.boot.simple.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apel.gaia.commons.pager.Condition;
import org.apel.gaia.commons.pager.Order;
import org.apel.gaia.commons.pager.PageBean;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractTccBizCommonService<T,PK extends Serializable> extends JpaRepository<T, Serializable>{
	/**
	 * 根据对象的ID查询对象信息
	 * @param id
	 * 			对象ID
	 * @return
	 * 			返回查询的对象
	 */
	public T findById(PK id);	
	
	
	/**
	 * 查询所有数据
	 */
	public List<T> findAll(Sort sort);
	
	/**
	 * 根据条件查询数据
	 * @param conditions 条件对象
	 */
	public List<T> findByCondition(Condition... conditions);
	
	/**
	 * 保存一个对象
	 * @param entity
	 * 			保存的对象
	 */
	<S extends T> S save(S entity);
	
	/**
	 * 修改一个对象
	 * @param entity
	 * 			修改的对象
	 */
	public void update(T entity);
	
	/**
	 * 根据ID删除一个或者多个对象
	 * @param ids
	 * 			删除的对象ID数组
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void deleteById(PK... ids);
	
	
	/**
	 * 分页查询
	 * @param pageBean 分页对象
	 */
	void pageQuery(PageBean pageBean);
	
	/**
	 * 保存对象<br/>
	 * 注意：如果对象id是字符串，并且没有赋值，该方法将自动设置为uuid值
	 * @param item
	 *            持久对象，或者对象集合
	 * @throws Exception
	 */
	public void store(Object... item);
	
	/**
	 * 更新对象数据
	 * 
	 * @param item
	 *            持久对象，或者对象集合
	 * @throws Exception
	 */
	public void update(Object... item);
	
	/**
	 * 执行ql语句
	 * @param qlString 基于jpa标准的ql语句
	 * @param values ql中的?参数值,单个参数值或者多个参数值
	 * @return 返回执行后受影响的数据个数
	 */
	public int executeUpdate(String qlString, Object... values);

	/**
	 * 执行ql语句
	 * @param qlString 基于jpa标准的ql语句
	 * @param params key表示ql中参数变量名，value表示该参数变量值
	 * @return 返回执行后受影响的数据个数
	 */
	public int executeUpdate(String qlString, Map<String, Object> params);
	
	/**
	 * 执行ql语句，可以是更新或者删除操作
	 * @param qlString 基于jpa标准的ql语句
	 * @param values ql中的?参数值
	 * @return 返回执行后受影响的数据个数
	 * @throws Exception
	 */
	public int executeUpdate(String qlString, List<Object> values);
	
	/**
	 * 结合提供的分页信息，获取指定条件下的数据对象
	 * @param pageBean 分页信息
	 * @param qlString 基于jpa标准的ql语句
	 */
	public void doPager(PageBean pageBean, String qlString);
	
	/**
	 * 结合提供的分页信息，获取指定条件下的数据对象
	 * @param pageBean 分页信息
	 * @param qlString 基于jpa标准的ql语句
	 * @param cacheable 是否启用缓存查询
	 */
	public void doPager(PageBean pageBean,String qlString,boolean cacheable);
	
	/**
	 * 结合提供的分页信息，获取指定条件下的数据对象
	 * @param pageBean 分页信息
	 * @param qlString 基于jpa标准的ql语句
	 * @param params key表示ql中参数变量名，value表示该参数变量值
	 */
	public void doPager(PageBean pageBean, String qlString,Map<String, Object> params);

	/**
	 * 结合提供的分页信息，获取指定条件下的数据对象
	 * @param pageBean 分页信息
	 * @param qlString 基于jpa标准的ql语句
	 * @param values ql中的?参数值
	 */
	public void doPager(PageBean pageBean, String qlString, List<Object> values);
	
	/**
	 * 结合提供的分页信息，获取指定条件下的数据对象
	 * @param pageBean 分页信息
	 * @param qlString 基于jpa标准的ql语句
	 * @param values ql中的?参数值
	 */
	public void doPager(PageBean pageBean, String qlString, Object... values);
	
	/**
	 * 本地sql进行分页查询
	 * @param pageBean
	 * @param qlString
	 * @param values
	 */
	public void doNativeSqlPager(PageBean pageBean, String qlString, List<Object> values);
	
	/**
	 * 批量删除数据对象
	 * @param entityClass
	 * @param primaryKeyValues
	 * @return
	 */
	public int batchDeleteByQl(Class<?> entityClass,Object...primaryKeyValues);
	
	/**
	 * 批量删除数据对象
	 * @param entityClass
	 * @param pKeyVals 主键是字符串形式的
	 * @return
	 * @throws Exception
	 */
	public int batchDeleteByQl(Class<?> entityClass,String...pKeyVals);
	
	/**
	 * @param qlString 查询hql语句
	 * @param values hql参数值
	 * @param conditions 查询条件
	 * @param orders 排序条件
	 * @param sqlable 是否是本地sql
	 * @return
	 */
	public List<?> doList(String qlString, List<Object> values, List<Condition> conditions, List<Order> orders, boolean sqlable);
	
	/**
	 * @param qlString 查询hql语句
	 * @param conditions 查询条件
	 * @param orders 排序条件
	 * @param sqlable 是否是本地sql
	 * @return
	 */
	public List<?> doList(String qlString, List<Condition> conditions, List<Order> orders, boolean sqlable);
	
	/**
	 * @param qlString 查询hql语句
	 * @param values hql参数值
	 * @return
	 */
	public List<?> doList(String qlString, List<Object> values);
	
	/**
	 * @param qlString 查询hql语句
	 * @param values hql参数值
	 * @param sqlable 是否是本地sql
	 * @return
	 */
	public List<?> doList(String qlString, List<Object> values ,boolean sqlable);
	
}
