package vsu.netcracker.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ToDo - добавить в приложение логирование (по возможности и желанию) + еще можно тесты для кода
// ToDo - повозиться с типами блюд в меню (у нас нет супов, но есть морепродукты и т. д.)
// https://www.toptal.com/java/rest-security-with-jwt-spring-security-and-java
// https://blog.sqreen.com/authentication-best-practices-vue/

/**
 * Class with entry point of our app
 *
 * @author Кушнеренко Виктор
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
