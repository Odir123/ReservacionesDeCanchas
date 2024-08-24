package org.esfe.controlers;

import org.esfe.entities.Usuario;
import org.esfe.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.esfe.repository.RolRepository;
import org.esfe.entities.Rol;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(Usuario usuario) {
        usuarioService.registrarUsuario(usuario);
        return "redirect:/reservar-cancha";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/rol")
    public String showRolPage(Model model) {
        model.addAttribute("rol", new Rol());
        return "usuario/rol"; // Asegúrate de que esta ruta esté bien definida.
    }

    @PostMapping("/guardarRol")
    public String guardarRol(@ModelAttribute Rol rol, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "usuario/rol"; // Retorna a la vista con errores
        }

        // No es necesario convertir a Boolean, ya que el tipo es Integer
        usuarioService.guardarRol(rol);

        model.addAttribute("message", "Rol guardado exitosamente");
        return "redirect:/usuario/rol";
    }

    private String guardarImagen(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("File is empty");
        }

        String filePath = "/path/to/save/location/" + file.getOriginalFilename();
        file.transferTo(new java.io.File(filePath));

        return filePath;
    }
}