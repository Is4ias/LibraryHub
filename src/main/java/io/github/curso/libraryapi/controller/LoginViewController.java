package io.github.curso.libraryapi.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginViewController {

    @GetMapping("/login")
    public String paginaLogin(){
        return "login";
    }
}
