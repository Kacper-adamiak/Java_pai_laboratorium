package com.example.jee_laboratorium_10.security;

import com.example.jee_laboratorium_10.user.UserDao;
import com.example.jee_laboratorium_10.user.UserDto;
import com.example.jee_laboratorium_10.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepository.existsByUsername(username)) {
            UserDao user = userRepository.findByUsername(username);
            return new User(user.getUsername(),
                    user.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException(
                    "User not found with username: " + username);
        }
    }

    public UserDao save(UserDto user) {
        UserDao newUser = new UserDao();
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bc.encode(user.getPassword()));
        return userRepository.save(newUser);
    }
}
