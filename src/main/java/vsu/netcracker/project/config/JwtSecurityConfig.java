package vsu.netcracker.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vsu.netcracker.project.security.JwtAuthenticationEntryPoint;
import vsu.netcracker.project.security.JwtAuthenticationProvider;
import vsu.netcracker.project.security.JwtAuthenticationTokenFilter;
import vsu.netcracker.project.security.JwtSuccessHandler;

import java.util.Collections;

/*
    ToDo - 1. как в TokenFilter запихнуть несколько урлов? (есть одна идея, но не факт что сработает)
        2. сделать бы относительные урлы (но тогда появляется OPTIONS 500)
        3. при неудачной авторизации (при каждом чихе возвращать на страницу логина)
        4. роли
        5. разобраться бы в этом коде
        6. аутентификация нового пользователя + возможно доп. фишки (remember me, смена пароля, регистрация через ВК и т. д.)
 */

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationProvider authenticationProvider;

    private final JwtAuthenticationEntryPoint entryPoint;

    public JwtSecurityConfig(JwtAuthenticationProvider authenticationProvider, JwtAuthenticationEntryPoint entryPoint) {
        this.authenticationProvider = authenticationProvider;
        this.entryPoint = entryPoint;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter() {
        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers(
                "/kitchen/**",
                "/waiter/**",
                "/admin/**",
                "/",
                "/cart/**",
                "/menu/**").authenticated()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();
    }
}
