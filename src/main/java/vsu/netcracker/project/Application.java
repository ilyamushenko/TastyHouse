package vsu.netcracker.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*  ToDo:
    1. Прикрутить Vue.js к страничкам
    2. Вместо шаблонизатора передавать и получать данные как-то по другому (с помощью JSON и RestController)
    Подправить подсчет времени в прогресс баре, выяснить почему в 1 столике не хватает места под нижнюю часть
*/

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
