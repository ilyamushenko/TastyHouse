/*
 * Project: TastyHouse
 * Class: CustomUserDetails
 * Created: Alina Popova / MISS-CEH4TOP
 * Date: 03.05.19 21:18
 */

package vsu.netcracker.project.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vsu.netcracker.project.database.models.Staff;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1256711395932122675L;
    private Staff staff;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(staff.getRoleStaff()).map(
                role -> new SimpleGrantedAuthority("ROLE_" + role.getTitle())
        ).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return staff.getPassword();
    }

    @Override
    public String getUsername() {
        return staff.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
