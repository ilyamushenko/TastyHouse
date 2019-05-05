///*
// * Project: TastyHouse
// * Class: CustomUserDetailsService
// * Created: Alina Popova / MISS-CEH4TOP
// * Date: 03.05.19 21:15
// */
//
//package vsu.netcracker.project.security;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import vsu.netcracker.project.database.models.Staff;
//import vsu.netcracker.project.database.service.StaffService;
//
//@Component
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final StaffService staffService;
//
//    public CustomUserDetailsService(StaffService staffService) {
//        this.staffService = staffService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        Staff staff = staffService.findByLogin(login);
//        CustomUserDetails userDetails;
//        if (staff != null) {
//            userDetails = new CustomUserDetails();
//            userDetails.setStaff(staff);
//        } else {
//            throw new UsernameNotFoundException("User not exist with name : " + login);
//        }
//        return userDetails;
//    }
//}
