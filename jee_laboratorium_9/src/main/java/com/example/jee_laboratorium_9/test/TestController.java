package com.example.jee_laboratorium_9.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping({"/welcome"})
    public String welcomePage() {
        return "Welcome!";
    }
}
