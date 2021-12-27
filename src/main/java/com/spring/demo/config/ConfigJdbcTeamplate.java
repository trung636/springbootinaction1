package com.spring.demo.config;

import java.net.URI;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigJdbcTeamplate {
	
	@Bean
	public JdbcTemplate jdbc() {
		System.out.println("############ConfigDatasource################");
	    return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/tacocl");
        dataSource.setUsername("root");
        dataSource.setPassword("Anhyeuemnhieu1");

        return dataSource;
	}
	
//	provide method crud resource
	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}

//	traverson navigate to the link where a new resource will be create
	@Bean
	public Traverson traverson() {
		return new Traverson(URI.create("http://localhost:8080/api"), MediaTypes.HAL_JSON);
	}

}
