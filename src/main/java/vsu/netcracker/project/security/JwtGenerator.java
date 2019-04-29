package vsu.netcracker.project.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import vsu.netcracker.project.database.models.Staff;

import java.util.Calendar;
import java.util.Date;

@Component
public class JwtGenerator {

    public String generate(Staff staff) {
        Claims claims = Jwts.claims()
                .setSubject(staff.getLogin());
        claims.put("id", String.valueOf(staff.getId()));
        claims.put("role", staff.getRoleStaff().getTitle());
        claims.put("password", staff.getPassword());
        claims.put("token_create_date", new Date().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 2);
        claims.put("token_expiration_date", calendar.getTime());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, "TastyHouse")
                .compact();
    }
}
