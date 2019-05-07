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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/kitchen**").access("hasAuthority('COOK')")
                .antMatchers("/waiter**").access("hasAuthority('WAITER')")
                .antMatchers("/admin**").access("hasAuthority('ADMIN')")
                .antMatchers("/", "/cart**", "/menu**").access("hasAuthority('GUEST')")
                .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler())
                //.defaultSuccessUrl("http://localhost:8081/")
                .permitAll()
                .and()
                .logout();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            System.out.println(authentication.getPrincipal());
            httpServletRequest.setAttribute("Staff", authentication.getPrincipal());
            httpServletResponse.sendRedirect("http://localhost:8081/");
        };
    }
}
