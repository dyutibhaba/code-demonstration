package com.protostellar.zugplaner.utils;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.jwt.*;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = "classpath:application-it.yml", factory = YamlPropertySourceFactory.class)
@ComponentScan(basePackages = "com.protostellar.zugplaner")
@EnableAutoConfiguration(exclude = {LiquibaseAutoConfiguration.class})
public class SpringIntegrationTestConfiguration {
  @Autowired
  private DataSource dataSource;

  public SpringIntegrationTestConfiguration(
  ) {
  }

  @Bean
  public SpringLiquibase liquibase() {
    SpringLiquibase liquibase = new SpringLiquibase();
    System.out.println("Datasource: " + dataSource);
    liquibase.setChangeLog("classpath:db/changelog/db.changelog-master-it.yaml");
    liquibase.setDataSource(dataSource);
    return liquibase;
  }

  @Bean
  public JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(dataSource);
  }
}
