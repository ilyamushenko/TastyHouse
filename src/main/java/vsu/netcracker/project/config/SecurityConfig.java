//package vsu.netcracker.project.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//// https://www.youtube.com/watch?v=3UOc3NTYHiI - туториал про security
//// https://www.youtube.com/watch?v=WPd7h6wD6-4&index=16&list=PLaWfw53gNyzaDTEmrlCCj1jjqr6770Nnp
//// ToDo - учесть роли
//// ToDo - логин через соцсети
//// ToDo - сообщения при удачном разлогинивании и неудачном логине
//// ToDo - чекбокс Remember me
//// ToDo - кнопка Logout
//// ToDo - перенаправление с регистрации на логин и обратно
//// ToDo - сделать аутентификацию через токены (видео выше)
//// ToDo - каким-то образом перенаправить внимание Security на порт 8081 (Андрей сказал, возможно будет на одном порту)
//
///**
// * Security class for login/logout
// *
// * @author Кушнеренко Виктор
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("http://localhost:8081/login").permitAll()
//                .and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(encoder.encode("password"))
//                .roles("USER");
//    }
//}
