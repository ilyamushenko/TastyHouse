package vsu.netcracker.project.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handle login/logout requests
 *
 * @author Кушнеренко Виктор
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class LoginController {

    @GetMapping("get_user_data")
    public Object getUserData() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
