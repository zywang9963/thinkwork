package com.thinkwork.config.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, Serializable>
        implements BaseRepository<T, Serializable>{

    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(BaseRepositoryImpl.class);

    private final EntityManager entityManager;

    private Class<T> clazz;

    //父类没有不带参数的构造方法，这里手动构造父类
    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.clazz = domainClass;
        this.entityManager = entityManager;
    }

    @Override
    public void store(Object... item) {
        if(null!=item){
            for(Object entity : item){
                entityManager.persist(entity);
            }
        }
    }

    @Override
    public void update(Object... item) {
        if (null != item) {
            for (Object entity : item) {
                logger.info("---------------merge");
                entityManager.merge(entity);
            }
        }
    }

    @Override
    public int executeUpdate(String qlString, Object... values) {
        Query query = entityManager.createQuery(qlString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }
        return query.executeUpdate();
    }

    @Override
    public int executeUpdate(String qlString, Map<String, Object> params) {
        Query query = entityManager.createQuery(qlString);
        for (String name : params.keySet()) {
            query.setParameter(name, params.get(name));
        }
        return query.executeUpdate();
    }

    @Override
    public int executeUpdate(String qlString, List<Object> values) {
        Query query = entityManager.createQuery(qlString);
        for (int i = 0; i < values.size(); i++) {
            query.setParameter(i + 1, values.get(i));
        }
        return query.executeUpdate();
    }

}
