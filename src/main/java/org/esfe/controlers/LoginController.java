package org.esfe.controlers;

import org.esfe.entities.Usuario;
import org.esfe.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

