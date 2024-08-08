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
            return "home/donde-estamos"; // Asegúrate de que el archivo se llame donde-estamos.html y esté en la carpeta templates/home
        }

        @GetMapping("/preguntas-frecuentes")
        public String preguntasFrecuentes() {
            return "home/preguntas-frecuentes";
        }

        @GetMapping("/precios")
        public String precios() {
            return "home/precios";
        }

        @GetMapping("/reservar-cancha")
        public String reservarCancha() {
            return "canchas/reservar";
        }
    }
