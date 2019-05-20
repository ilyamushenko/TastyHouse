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

import javax.servlet.http.HttpSession;
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
                .usersByUsernameQuery("select pgp_sym_decrypt(login, 'secretKey'), pgp_sym_decrypt(password, 'secretKey'), enabled from staff where pgp_sym_decrypt(login, 'secretKey') = ?")
                .authoritiesByUsernameQuery("select pgp_sym_decrypt(staff_login, 'secretKey'), title from role_staff where pgp_sym_decrypt(staff_login, 'secretKey') = ?")
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/kitchen/**", "/kitchen**").hasAnyAuthority("COOK", "ADMIN")
                .antMatchers("/waiter/**", "/waiter**").hasAnyAuthority("WAITER", "ADMIN")
                .antMatchers("/admin/**", "/admin**").hasAuthority("ADMIN")
                .antMatchers(  "/menu/**", "/menu**").access("hasRole('ANONYMOUS') or hasAnyAuthority('ADMIN', 'GUEST')")
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
