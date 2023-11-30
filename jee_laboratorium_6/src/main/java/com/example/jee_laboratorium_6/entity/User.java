package com.example.jee_laboratorium_6.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userid;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String login;
    @NotBlank
    @Size(min = 4)
    private String password;
    public User() {
    }
    public User(String name, String surname, String login,
                String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }
}
