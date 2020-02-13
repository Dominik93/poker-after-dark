package com.slusarz.pokerafterdark.spring.configuration.persistence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class HiberanteProperties {

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String dialect;

    @Value("${spring.jpa.properties.hibernate.generate_statistics}")
    private String generateStatistics;

    @Value("${spring.jpa.properties.hibernate.order_inserts}")
    private boolean orderInserts;

    @Value("${spring.jpa.properties.hibernate.order_updates}")
    private boolean orderUpdates;

    @Value("${spring.jpa.properties.hibernate.show_sql}")
    private boolean showSql;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_versioned_data}")
    private boolean batchVersionedData;

    public Properties getAdditionalProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.generate_statistics", generateStatistics);
        properties.put("hibernate.order_inserts", orderInserts);
        properties.put("hibernate.order_updates", orderUpdates);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.jdbc.batch_size", batchSize);
        properties.put("hibernate.jdbc.batch_versioned_data", batchVersionedData);
        return properties;
    }
}
