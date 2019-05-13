/*
 * Project: TastyHouse
 * Class: AppConfig
 * Created: Alina Popova / MISS-CEH4TOP
 * Date: 05.05.19 15:58
 */

package vsu.netcracker.project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql:TastyHouse");
        dataSource.setUsername("postgres");
        dataSource.setPassword("0000");
        return dataSource;
    }
}
