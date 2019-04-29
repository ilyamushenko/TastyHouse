package vsu.netcracker.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// ToDo - добавить в приложение логирование (Можно сделать с помощью lombok по возможности и желанию) + еще можно тесты для кода
// ToDo - повозиться с типами блюд в меню (у нас нет супов, но есть морепродукты и т. д.)
// https://www.toptal.com/java/rest-security-with-jwt-spring-security-and-java
// https://blog.sqreen.com/authentication-best-practices-vue/

// https://dzone.com/articles/spring-hibernate-ehcache-caching - кэширование

/**
 * Class with entry point of our app
 *
 * @author Кушнеренко Виктор
 */
@SpringBootApplication
@Controller
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
