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
@EnableJpaRepositories(basePackages="com.repo2",transactionManagerRef="transactionManagerref2",entityManagerFactoryRef="entityManagerfactoryref2")
public class DBConfig2 {


	@Autowired
	private Environment env;

	@Bean(name = "dataSource2")
	public DataSource getDataSource() {
		System.out.println("===================GETTING DATASOURCE=========================");
		System.out.println("PASSWORD::::" + env.getProperty("spring.datasource2.password"));
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(env.getProperty("spring.datasource2.driver-class-name"));
		dataSourceBuilder.url(env.getProperty("spring.datasource2.url"));
		dataSourceBuilder.username(env.getProperty("spring.datasource2.username"));
		dataSourceBuilder.password(env.getProperty("spring.datasource2.password"));
		return dataSourceBuilder.build();
	}
	@Bean("entityManagerfactoryref2")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource2") DataSource datasource,EntityManagerFactoryBuilder builder) {
		return builder.dataSource(datasource).packages("com.domain2").persistenceUnit("bar").build();
	}
    @Bean("transactionManagerref2")
    public PlatformTransactionManager platformManagerFactory(@Qualifier("entityManagerfactoryref2")EntityManagerFactory entityManagerFactory) {
    	return new JpaTransactionManager(entityManagerFactory);
    }

}
