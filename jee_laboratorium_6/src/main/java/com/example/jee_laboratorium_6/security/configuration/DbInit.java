package com.example.jee_laboratorium_6.security.configuration;

import com.example.jee_laboratorium_6.dao.UserDao;
import com.example.jee_laboratorium_6.entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DbInit {
    @Autowired
    private UserDao dao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        dao.save(new User("Piotr", "Piotrowski","admin",
                passwordEncoder.encode("admin")));
        dao.save(new User("Ania", "Annowska","ania",
                passwordEncoder.encode("ania")));
    }
}
