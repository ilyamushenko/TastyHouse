package vsu.netcracker.project.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Controller class for handle login/logout requests
 *
 * @author Кушнеренко Виктор
 */
//@CrossOrigin(origins = "http://localhost:8081")
//@RestController
//public class LoginController {
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public Map<Object, Object> login(@RequestParam(value = "error", required = false) String error,
//                       @RequestParam(value = "logout", required = false) String logout) {
//
//        Map<Object, Object> map = new LinkedHashMap<>();
//        if (error != null) {
//            map.put("error", "Invalid username and password!");
//        }
//
//        if (logout != null) {
//            map.put("msg", "You've been logged out successfully.");
//        }
//        // model.setViewName("login");
//
//        return map;
//    }
//}
