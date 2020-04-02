package com.wd.springboot08jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author dwang
 * @create 2019-12-16 14:51
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.wd.springboot08jpa.dao2",
        entityManagerFactoryRef = "localContainerEntityManagerFactoryBeanTwo",
        transactionManagerRef = "platformTransactionManagerTwo")
public class JpaConfigTwo {

    @Autowired
    @Qualifier(value = "dsTwo")
    DataSource dsTwo;

    @Autowired
    JpaProperties jpaProperties;


    @Bean
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBeanTwo(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dsTwo)
                .packages("com.wd.springboot08jpa.entity")
                .properties(jpaProperties.getProperties())
                .persistenceUnit("pu2")
                .build();
    }

    @Bean
    PlatformTransactionManager platformTransactionManagerTwo(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean factoryBeanTwo = localContainerEntityManagerFactoryBeanTwo(builder);
        return new JpaTransactionManager(factoryBeanTwo.getObject());
    }

}
