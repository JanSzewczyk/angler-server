package pl.angler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import pl.angler.entity.User;
import pl.angler.exception.UnauthorizedException;
import pl.angler.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserAuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) {
        Optional<User> findUser = userRepository.findByEmail(s);

        if (!findUser.isPresent()) {
            throw new UnauthorizedException("Invalid email.");
        }

        User user = findUser.get();
        if (!user.isAuthenticated()) {
            throw new UnauthorizedException("Your e-mail has not been confirmed, check your mailbox.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);

        return userDetails;
    }
}
