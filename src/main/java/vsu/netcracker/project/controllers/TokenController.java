package vsu.netcracker.project.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vsu.netcracker.project.database.models.Staff;
import vsu.netcracker.project.database.service.StaffService;
import vsu.netcracker.project.security.JwtGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/token")
public class TokenController {

    private JwtGenerator jwtGenerator;
    private StaffService staffService;

    public TokenController(JwtGenerator jwtGenerator, StaffService staffService) {
        this.jwtGenerator = jwtGenerator;
        this.staffService = staffService;
    }

    @PostMapping
    public List<Object> generate(@RequestBody Map<String, Object> json) {
        String login = (String) json.get("login");
        Staff staff = staffService.findByLogin(login);
        List<Object> arr = new ArrayList<>();
        arr.add(jwtGenerator.generate(staff));
        arr.add(staff);
        return arr;
    }
}
