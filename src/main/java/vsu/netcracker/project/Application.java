package vsu.netcracker.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

// ToDo - добавить в приложение логирование (по возможности и желанию) + еще можно тесты для кода
// ToDo - повозиться с типами блюд в меню (у нас нет супов, но есть морепродукты и т. д.)

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
