package vsu.netcracker.project.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import vsu.netcracker.project.database.models.Staff;

@Component
public class JwtGenerator {

    public String generate(Staff staff) {
        Claims claims = Jwts.claims()
                .setSubject(staff.getLogin());
        claims.put("id", String.valueOf(staff.getId()));
        claims.put("role", staff.getRoleStaff().getTitle());
        claims.put("password", staff.getPassword());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, "TastyHouse")
                .compact();
    }
}
