package org.esfe.controlers;

import org.esfe.entities.Usuario;
import org.esfe.interfaces.RolService;
import org.esfe.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/usuario") // URL para el controlador de Usuario
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @GetMapping("/index")
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Usuario> usuarios = usuarioService.findAll(pageable);
        model.addAttribute("usuarios", usuarios);

        int totalPages = usuarios.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "Usuario/index";
    }


    @GetMapping("/details/{id}")
    public String getDetails(@PathVariable("id") Integer id, Model model) {
        Optional<Usuario> usuario = usuarioService.findOneById(id);
        if (usuario.isEmpty()) {
            // Manejo de error o redirección
            model.addAttribute("error", "Usuario no encontrado");
            return "error"; // Asegúrate de tener una página de error configurada
        }
        model.addAttribute("usuario", usuario.get());
        return "Usuario/details";
    }

    @PostMapping("/details")
    public String postDetails(@RequestParam("id") Integer id, Model model) {
        Optional<Usuario> usuario = usuarioService.findOneById(id);
        if (usuario.isEmpty()) {
            // Manejo de error o redirección
            model.addAttribute("error", "Usuario no encontrado");
            return "error"; // Asegúrate de tener una página de error configurada
        }
        model.addAttribute("usuario", usuario.get());
        return "Usuario/details";
    }

    @GetMapping("/create")
    public String showCreateForm(Usuario usuario, Model model) {
        model.addAttribute("roles", rolService.getAll()); // Assuming you have a service to fetch roles
        return "usuario/create";
    }


    @PostMapping("/save")
    public String save(@RequestParam("rol") Integer rol, Usuario usuario, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("usuario", usuario);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "usuario/create";
        }

        usuarioService.createOrEditOne(usuario);
        attributes.addFlashAttribute("msg", "Usuario creado correctamente");
        return "redirect:/usuario";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Optional<Usuario> usuario = usuarioService.findOneById(id);
        if (usuario.isEmpty()) {
            return "redirect:/usuario";
        }
        model.addAttribute("usuario", usuario.get());
        return "Usuario/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        Optional<Usuario> usuario = usuarioService.findOneById(id);
        if (usuario.isEmpty()) {
            return "redirect:/usuario";
        }
        model.addAttribute("usuario", usuario.get());
        return "Usuario/delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Usuario usuario, RedirectAttributes attributes) {
        usuarioService.deleteOneById(usuario.getId());
        attributes.addFlashAttribute("msg", "Usuario eliminado correctamente");
        return "redirect:/usuario";
    }
}
