package com.thinkwork.config.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 自定义Repository的方法接口
 *
 * @param <T>         领域对象即实体类
 * @param <ID>领域对象的注解
 * @author xiaowen
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    /**
     * 保存对象<br/>
     * 注意：如果对象id是字符串，并且没有赋值，该方法将自动设置为uuid值
     *
     * @param item 持久对象，或者对象集合
     * @throws Exception
     */
    public void store(Object... item);

    /**
     * 更新对象数据
     *
     * @param item 持久对象，或者对象集合
     * @throws Exception
     */
    public void update(Object... item);

    /**
     * 执行ql语句
     *
     * @param qlString 基于jpa标准的ql语句
     * @param values   ql中的?参数值,单个参数值或者多个参数值
     * @return 返回执行后受影响的数据个数
     */
    public int executeUpdate(String qlString, Object... values);

    /**
     * 执行ql语句
     *
     * @param qlString 基于jpa标准的ql语句
     * @param params   key表示ql中参数变量名，value表示该参数变量值
     * @return 返回执行后受影响的数据个数
     */
    public int executeUpdate(String qlString, Map<String, Object> params);

    /**
     * 执行ql语句，可以是更新或者删除操作
     *
     * @param qlString 基于jpa标准的ql语句
     * @param values   ql中的?参数值
     * @return 返回执行后受影响的数据个数
     * @throws Exception
     */
    public int executeUpdate(String qlString, List<Object> values);
}