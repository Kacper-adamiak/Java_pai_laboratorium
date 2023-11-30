package com.example.jee_laboratorium_6.dao;

import com.example.jee_laboratorium_6.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends CrudRepository<User, Integer> {
    void deleteById(int id);
    long deleteByName(String name);
    @Query("select u from User u")
    List<User> findAll();
    public Optional<User> findByLogin(String login);
}
