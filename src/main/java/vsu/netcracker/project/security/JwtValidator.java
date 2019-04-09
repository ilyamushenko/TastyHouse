package vsu.netcracker.project.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import vsu.netcracker.project.database.models.RoleStaff;
import vsu.netcracker.project.database.models.Staff;
import vsu.netcracker.project.database.service.RoleStaffService;

@Component
public class JwtValidator {

    private String secret = "TastyHouse";

    private final RoleStaffService roleStaffService;

    public JwtValidator(RoleStaffService roleStaffService) {
        this.roleStaffService = roleStaffService;
    }

    public Staff validate(String token) {
        Staff staff = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            staff = new Staff();
            staff.setLogin(body.getSubject());
            staff.setPassword(String.valueOf(body.get("password")));
            staff.setId((Integer) body.get("id"));
            RoleStaff roleStaff = roleStaffService.findByTitle(String.valueOf(body.get("role")));
            staff.setRoleStaff(roleStaff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staff;
    }
}
