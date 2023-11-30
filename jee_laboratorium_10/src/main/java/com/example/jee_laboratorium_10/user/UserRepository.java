package com.example.jee_laboratorium_10.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDao, Long> {
    UserDao findByUsername(String username);

    boolean existsByUsername(String username);
}
