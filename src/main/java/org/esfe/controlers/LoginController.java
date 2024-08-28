package org.esfe.controlers;

import org.esfe.entities.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        {return "usuario/login";}
    }
    @PostMapping("/register")
    public String registerUser(Usuario usuario) {

        return "redirect:/login";
    }
    @GetMapping("/register")
    public String register() {
        {
            return "usuario/register";
        }

    }
}
