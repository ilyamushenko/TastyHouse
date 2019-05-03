/*
 * Project: TastyHouse
 * Class: CustomUserDetailsService
 * Created: Alina Popova / MISS-CEH4TOP
 * Date: 03.05.19 21:15
 */

package vsu.netcracker.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import vsu.netcracker.project.database.models.Staff;
import vsu.netcracker.project.database.service.StaffService;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StaffService staffService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Staff staff = staffService.findByLogin(login);
        CustomUserDetails userDetails;
        if (staff != null) {
            userDetails = new CustomUserDetails();
            userDetails.setStaff(staff);
        } else {
            throw new UsernameNotFoundException("User not exist with name : " + login);
        }
        return userDetails;
    }
}
