package com.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.repo",transactionManagerRef="transactionManagerref",entityManagerFactoryRef="entityManagerfactoryref")
public class DBConfig {

	@Autowired
	private Environment env;

	@Primary
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		System.out.println("===================GETTING DATASOURCE=========================");
		System.out.println("PASSWORD::::" + env.getProperty("spring.datasource1.password"));
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(env.getProperty("spring.datasource1.driver-class-name"));
		dataSourceBuilder.url(env.getProperty("spring.datasource1.url"));
		dataSourceBuilder.username(env.getProperty("spring.datasource1.username"));
		dataSourceBuilder.password(env.getProperty("spring.datasource1.password"));
		return dataSourceBuilder.build();
	}
	@Primary
	@Bean("entityManagerfactoryref")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource datasource,EntityManagerFactoryBuilder builder) {
		return builder.dataSource(datasource).packages("com.domain").persistenceUnit("foo").build();
	}
	@Primary
    @Bean("transactionManagerref")
    public PlatformTransactionManager platformManagerFactory(@Qualifier("entityManagerfactoryref")EntityManagerFactory entityManagerFactory) {
    	return new JpaTransactionManager(entityManagerFactory);
    }
}
