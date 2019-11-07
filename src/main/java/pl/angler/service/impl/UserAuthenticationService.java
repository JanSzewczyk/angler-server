package pl.angler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import pl.angler.entity.User;
import pl.angler.exception.NotFoundException;
import pl.angler.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) {

        System.out.println("UsERNAME ::" + s);

        User user = userRepository.findByEmail(s);

        System.out.println("USERRRRRRRRRRR: " + user);

        if(user == null) {
            throw new NotFoundException("Invalid email.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);

        //todo delete
        System.out.println("DATA : " + userDetails);

        return userDetails;
    }
}
