package com.thinkwork.Service.base;

import com.thinkwork.config.jpa.BaseRepository;
import com.thinkwork.config.jpa.BaseRepositoryFactoryBean;
import com.thinkwork.config.jpa.BaseRepositoryImpl;
import com.thinkwork.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
public abstract class BaseService {
    protected static Logger logger = LoggerFactory.getLogger(BaseService.class);

    public abstract BaseEntity findById(Integer sid);

    public abstract <T> T findAll();

    public abstract <T> T findAllCriteria(Integer page, Integer size, HashMap querySet);
}
