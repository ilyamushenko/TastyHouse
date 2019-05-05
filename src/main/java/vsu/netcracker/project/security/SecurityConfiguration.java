/*
 * Project: TastyHouse
 * Class: WebMvcConfig
 * Created: Alina Popova / MISS-CEH4TOP
 * Date: 02.05.19 20:43
 */

package vsu.netcracker.project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    public SecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select login,password,enabled from staff where login = ?")
                .authoritiesByUsernameQuery("select staff_login, title from role_staff where staff_login = ?")
                .passwordEncoder(passwordEncoder());
    }

    // "/kitchen/**", "/waiter/**", "/admin/**", "/", "/cart/**", "/menu/**"

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/kitchen**").access("hasRole('COOK')")
                .antMatchers("/waiter**").access("hasRole('WAITER')")
                .antMatchers("/admin**").access("hasRole('ADMIN')")
                .antMatchers("/", "/cart**", "/menu**").access("hasRole('GUEST')")
//                .anyRequest()
//                .authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .and()
                .csrf();
//                .antMatchers("/kitchen/**").access("hasRole('COOK')")
//                .antMatchers("/waiter/**").access("hasRole('WAITER')")
//                .antMatchers("/admin/**").access("hasRole('ADMIN')")
//                .antMatchers("/", "/cart/**", "/menu/**").access("hasRole('GUEST')")
    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
