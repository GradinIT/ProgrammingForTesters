package se.jensen;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;


@Configuration
public class LiquibaseConfigurer {

    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource dataSource;

    @Bean
    @DependsOn
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase/liquibase-changelog.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}