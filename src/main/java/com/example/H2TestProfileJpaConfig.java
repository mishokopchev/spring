package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by mihailkopchev on 3/7/18.
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.example.repositories"})
@EnableTransactionManagement
public class H2TestProfileJpaConfig {


    @Bean
    @Profile("test")
    DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.h2.Driver");
        driverManagerDataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
        driverManagerDataSource.setUsername("sa");
        driverManagerDataSource.setPassword("sa");
        return driverManagerDataSource;
    }
}
