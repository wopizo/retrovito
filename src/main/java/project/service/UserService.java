package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.domain.Role;
import project.domain.User;
import project.repos.UserRepo;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean uddUser(User user, String name, String sname, String fname, String email, String phone, String city){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb != null){
            return false;
        }

        user.setActive(true);
        user.setBlocked(false);
        user.setName(name);
        user.setSname(sname);
        user.setFname(fname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setCity(city);
        user.setRoles(Collections.singleton(Role.USER));

        userRepo.save(user);

        return true;
    }
}
