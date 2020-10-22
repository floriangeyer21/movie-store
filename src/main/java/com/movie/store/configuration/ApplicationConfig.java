package com.movie.store.configuration;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {
        "com.movie.store.dao",
        "com.movie.store.service",
        "com.movie.store.security",
        "com.movie.store.model.dto"
})
public class ApplicationConfig {
    private final Environment environment;

    public ApplicationConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driver"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.username"));
        dataSource.setPassword(environment.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        Properties properties = new Properties();
        properties.put("hibernate.format_sql",
                environment.getProperty("hibernate.format_sql"));
        properties.put("hibernate.generate_statistics",
                environment.getProperty("hibernate.generate_statistics"));
        properties.put("hibernate.hbm2ddl.auto",
                environment.getProperty("hibernate.hbm2ddl.auto"));
        factoryBean.setHibernateProperties(properties);
        factoryBean.setPackagesToScan("com.movie.store.model");
        return factoryBean;
    }
}
