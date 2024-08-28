package org.esfe.controlers;

import org.esfe.entities.Rol;
import org.esfe.interfaces.RolService;
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
@RequestMapping("/rol") // URL corregida a minúsculas para evitar inconsistencias
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Rol> roles = rolService.findAll(pageable);
        model.addAttribute("roles", roles); // Corregido para asegurarse de que la variable en el modelo sea consistente

        int totalPages = roles.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "Rol/index"; // Verifica que esta vista exista en el directorio correcto
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        Optional<Rol> rol = rolService.findOneById(id);
        if (!rol.isPresent()) {
            return "redirect:/rol";
        }
        model.addAttribute("rol", rol.get());
        return "Rol/details";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("rol", new Rol()); // Añade un nuevo objeto Rol al modelo
        return "Rol/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Rol rol, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute("rol", rol);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "Rol/create";
        }

        rolService.createOrEditOne(rol);
        attributes.addFlashAttribute("msg", "Rol creado correctamente");
        return "redirect:/rol";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Optional<Rol> rol = rolService.findOneById(id);
        if (!rol.isPresent()) {
            return "redirect:/rol";
        }
        model.addAttribute("rol", rol.get());
        return "Rol/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        Optional<Rol> rol = rolService.findOneById(id);
        if (!rol.isPresent()) {
            return "redirect:/rol";
        }
        model.addAttribute("rol", rol.get());
        return "Rol/delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Rol rol, RedirectAttributes attributes){
        rolService.deleteOneById(rol.getId());
        attributes.addFlashAttribute("msg", "Rol eliminado correctamente");
        return "redirect:/rol";
    }
}
