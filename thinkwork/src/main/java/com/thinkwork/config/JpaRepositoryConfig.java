package com.thinkwork.config;

import com.thinkwork.config.jpa.BaseRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.thinkwork.repository"}, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class JpaRepositoryConfig {
}
