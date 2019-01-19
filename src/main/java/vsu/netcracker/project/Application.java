package vsu.netcracker.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*  ToDo:
    1. Прикрутить Vue.js к страничкам
    2. Вместо шаблонизатора передавать и получать данные как-то по другому
    3. Спросить у ребят по поводу 2 доп. полей: URL изображения для блюда и вида оплаты

    Второстепенные задачи:
    4. Генерация через wro4j
    5. Можно еще страницы, обрабатывающие ошибки сделать
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
