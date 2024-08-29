package org.esfe.controlers;

import org.esfe.entities.Cancha;
import org.esfe.interfaces.CanchaService;
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
@RequestMapping("/cancha")
public class CanchaController {

    @Autowired
    private CanchaService canchaService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Cancha> canchas = canchaService.findAll(pageable);
        model.addAttribute("canchas", canchas);

        int totalPages = canchas.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "canchas/index";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Long id, Model model) {
        Optional<Cancha> cancha = canchaService.findOneById(id);
        if (!cancha.isPresent()) {
            return "redirect:/cancha";
        }
        model.addAttribute("cancha", cancha.get());
        return "canchas/details";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("cancha", new Cancha());
        return "canchas/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Cancha cancha, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("cancha", cancha);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "canchas/create";
        }

        canchaService.createOrEditOne(cancha);
        attributes.addFlashAttribute("msg", "Cancha creada correctamente");
        return "redirect:/cancha";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Optional<Cancha> cancha = canchaService.findOneById(id);
        if (!cancha.isPresent()) {
            return "redirect:/cancha";
        }
        model.addAttribute("cancha", cancha.get());
        return "canchas/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id, Model model) {
        Optional<Cancha> cancha = canchaService.findOneById(id);
        if (!cancha.isPresent()) {
            return "redirect:/cancha";
        }
        model.addAttribute("cancha", cancha.get());
        return "canchas/delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Cancha cancha, RedirectAttributes attributes) {
        canchaService.deleteOneById(cancha.getId());
        attributes.addFlashAttribute("msg", "Cancha eliminada correctamente");
        return "redirect:/cancha";
    }
}
