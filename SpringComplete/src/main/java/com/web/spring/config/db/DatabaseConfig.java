package com.web.spring.config.db;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.web.spring.config.encode.AES;
import com.zaxxer.hikari.HikariConfig;					
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig {
	@Value("${db.datasource.driverClassName}")
	private String driverClassname;
	
	@Value("${db.datasource.username}")
	private String userName;
	
	@Value("${db.datasource.password}")
	private String password;
	
	@Value("${db.datasource.url}")
	private String urlDB;
	
	private String secrectKey = "Aa@1234";
	
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(AES.decrypt(urlDB, secrectKey));
		hikariConfig.setDriverClassName(driverClassname);
		hikariConfig.setUsername(AES.decrypt(userName, secrectKey));
		hikariConfig.setPassword(AES.decrypt(password, secrectKey));
		
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		
		return hikariDataSource;
	}
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager dataSourceTransactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new  SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/web/spring/mapper/sql*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
}














