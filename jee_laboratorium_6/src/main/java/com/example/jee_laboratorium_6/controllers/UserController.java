package com.example.jee_laboratorium_6.controllers;


import com.example.jee_laboratorium_6.dao.UserDao;
import com.example.jee_laboratorium_6.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao dao;
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/register")
    public String registerPage(Model m) {
        m.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String registerPagePOST(@Valid @ModelAttribute User user, BindingResult binding) {
        if(dao.findByLogin(user.getLogin()).isPresent()){
            binding.addError(new FieldError("user", "login", "Podany login już istnieje"));
        }

        if (binding.hasErrors()) {
            return "register";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);

        return "redirect:/login";
    }
    @GetMapping("/profile")
    public String profilePage(Model m, Principal principal) {
        m.addAttribute("user", dao.findByLogin(principal.getName()).get());
        return "profile";
    }
    @GetMapping("/users")
    public String users(Model m){
        List<User> users = dao.findAll();
        m.addAttribute("users",users);
        return "users";
    }
    @GetMapping("/deleteacc")
    public String deleteAccount(Principal principal){
        dao.deleteById(dao.findByLogin(principal.getName()).get().getUserid());
        return "redirect:/logout";
    }

    @GetMapping("/edit")
    public String editGetProfile(Model m, Principal principal) {
        m.addAttribute("userData", dao.findByLogin(principal.getName()).get());
        return "edit";
    }

    @PostMapping("/edit")
    public String editProfile(@Valid @ModelAttribute("userData") User userData, BindingResult binding, Principal principal) {
        if(dao.findByLogin(userData.getLogin()).isPresent() & !principal.getName().equals(userData.getLogin())){
            binding.addError(new FieldError("userData", "login", "Podany login już istnieje"));
        }

        if (binding.hasErrors()) {
            return "edit";
        }

        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        dao.save(userData);

        return "redirect:/logout";
    }
}
