/*
 * Project: TastyHouse
 * Class: StatusDish
 * Created: Alina Popova / MISS-CEH4TOP
 * Date: 09.04.2019, 1:28
 */

package vsu.netcracker.project.database.models.enums;

import java.util.*;
import java.util.stream.Collectors;

public enum StatusDish {
    available("Доступно"),
    no_ingredients("Нет ингредиентов"),
    blocked("Заблокировано"),
    deleted("Удалено");

    private String rusName;
    StatusDish(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return rusName;
    }

    public void setRusName(String rusName) {
        this.rusName = rusName;
    }

    public static List<Map<String, String>> getAll () {
        List<Map<String, String>> result = new LinkedList<>();
        for(StatusDish statusDish: values()) {
            Map<String, String> map = new HashMap<>();
            map.put("id", statusDish.name());
            map.put("value", statusDish.rusName);
            result.add(map);
        }
        return result;
    }
}
