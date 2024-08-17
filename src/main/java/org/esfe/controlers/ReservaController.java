package org.esfe.controlers;

import org.esfe.entities.Cancha;
import org.esfe.entities.Reserva;
import org.esfe.entities.Usuario;
import org.esfe.repository.CanchaRepository;
import org.esfe.repository.ReservaRepository;
import org.esfe.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private CanchaRepository canchaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/nueva")
    public ModelAndView mostrarFormulario() {
        ModelAndView modelAndView = new ModelAndView("reserva-form");
        modelAndView.addObject("canchaList", canchaRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/crear")
    public String crearReserva(@RequestParam("cancha") Integer canchaId,
                               @RequestParam("fecha") String fechaStr,
                               @RequestParam("horas") String horas,
                               @RequestParam("usuario") Integer usuarioId,
                               @RequestParam("estado") String estado) {
        Optional<Cancha> cancha = canchaRepository.findById(canchaId.longValue());
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId.longValue());

        if (cancha.isPresent() && usuario.isPresent()) {
            Reserva reserva = new Reserva();
            reserva.setCancha(cancha.get());
            reserva.setFechaReserva(LocalDate.parse(fechaStr));
            reserva.setHoras(horas);
            reserva.setUsuario(usuario.get());
            reserva.setEstado(estado);
            reservaRepository.save(reserva);
            return "redirect:/reservas/nueva?success";
        }
        return "redirect:/reservas/nueva?error";
    }


}

