package vsu.netcracker.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vsu.netcracker.project.model.JwtUser;
import vsu.netcracker.project.security.JwtGenerator;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final JwtGenerator jwtGenerator;

    @Autowired
    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping
    public String generate(@RequestBody final JwtUser jwtUser) {
        System.out.println(jwtUser);
        return jwtGenerator.generate(jwtUser);
    }
}
