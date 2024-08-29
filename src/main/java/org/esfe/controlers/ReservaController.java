package org.esfe.controlers;

import org.springframework.web.bind.annotation.GetMapping;

public class ReservaController {

    @GetMapping("/index")
    public String index() {
        return "Reserva/index";
    }
}
