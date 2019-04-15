package vsu.netcracker.project.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Controller class for handle login/logout requests
 *
 * @author Кушнеренко Виктор
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public Map<String, Object> getLogin(@RequestParam(value = "error", required = false) String error,
                                        @RequestParam(value = "logout", required = false) String logout) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("logout", logout != null);
        map.put("error", error != null);
        return map;
    }
}
