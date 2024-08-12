package org.esfe.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String index(){return "home/index";}




        @GetMapping("/privacy")
        public String privacy(){
            return "home/privacy";
        }

        @GetMapping("/terms")
        public String terms(){
            return "home/terms";
        }
    @GetMapping("/donde-estamos")
    public String dondeEstamos() {
        return "Home/donde-estamos";
    }

    @GetMapping("/preguntas-frecuentes")
    public String preguntasFrecuentes() {
        return "Home/preguntas-frecuentes";
    }

    @GetMapping("/precios")
    public String precios() {
        return "canchas/precios";
    }

        @GetMapping("/reservar-cancha")
        public String reservarCancha() {
            return "canchas/reservar";
        }
    }
