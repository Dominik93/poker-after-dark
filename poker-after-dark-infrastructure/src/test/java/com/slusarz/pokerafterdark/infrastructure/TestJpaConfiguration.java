package com.slusarz.pokerafterdark.infrastructure;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class TestJpaConfiguration {

    private static final String DEFAULT_URL
            = "jdbc:h2:mem:tcp:db;DB_CLOSE_DELAY=-1;MODE=Oracle;INIT=RUNSCRIPT FROM 'classpath:h2_init.sql'";

    @Value("${test.db.driverClassName:org.h2.Driver}")
    private String driverClassName;

    @Value("${test.db.url:" + DEFAULT_URL + "}")
    private String url;

    @Value("${test.db.username:poker}")
    private String username;

    @Value("${test.db.password:poker}")
    private String password;

    @Value("${test.hibernate.hbm2ddl.auto:create}")   // validate | create
    private String hbm2ddlMode;

    @Value("${test.hibernate.dialect:org.hibernate.dialect.H2Dialect}")
    private String hibernateDialect;

    @Value("${test.hibernate.format_sql:true}")
    private String formatSql;

    @Value("${test.hibernate.use_sql_comments:true}")
    private String commentSql;

    @Value("${test.hibernate.show_sql:true}")
    private String showSql;

    @Bean(name = "dataSource")
    DataSource readDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(username);

        return dataSource;
    }

    @Bean(name = "entityManagerFactory")
    LocalContainerEntityManagerFactoryBean entityManagerFactory(final @Qualifier("dataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.slusarz.pokerafterdark.infrastructure");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean(name = "transactionManager")
    PlatformTransactionManager transactionManager(final @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", hbm2ddlMode);
        properties.setProperty("hibernate.dialect", hibernateDialect);
        properties.setProperty("hibernate.format_sql", formatSql);
        properties.setProperty("hibernate.show_sql", showSql);
        properties.setProperty("hibernate.use_sql_comments", showSql);
        return properties;
    }
}
