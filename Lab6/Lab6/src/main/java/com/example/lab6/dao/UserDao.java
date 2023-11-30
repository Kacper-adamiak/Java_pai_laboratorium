package com.example.lab6.dao;

import com.example.lab6.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Integer> {
    User findByLogin(String login);
}
