package vsu.netcracker.project.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping
public class LoginController {

    @GetMapping("/get_user_data")
    public Object getUserData() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
