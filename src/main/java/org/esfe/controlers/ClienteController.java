package org.esfe.controlers;

import org.esfe.entities.Cliente;
import org.esfe.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/index")
    public String showIndexPage(Model model) {
        List<Cliente> clientes = clienteService.findAllClientes();
        model.addAttribute("clientes", clientes);
        return "cliente/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente/create";
    }

    @PostMapping("/create")
    public String createCliente(Cliente cliente) {
        clienteService.saveCliente(cliente);
        return "redirect:/cliente/index"; // Redirect to the index page after creation
    }

    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable("id") Integer id, Model model) {
        Cliente cliente = clienteService.findClienteById(id);
        model.addAttribute("cliente", cliente);
        return "cliente/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateCliente(@PathVariable("id") Integer id, Cliente cliente) {
        cliente.setId(id); // Ensure the correct ID is set
        clienteService.updateCliente(cliente);
        return "redirect:/cliente/index"; // Redirect to the index page after update
    }

    @GetMapping("/delete/{id}")
    public String showDeletePage(@PathVariable("id") Integer id, Model model) {
        Cliente cliente = clienteService.findClienteById(id);
        model.addAttribute("cliente", cliente);
        return "cliente/delete";
    }

    @PostMapping("/delete")
    public String deleteCliente(@RequestParam("id") Integer id) {
        clienteService.deleteCliente(id);
        return "redirect:/cliente/index";
    }

    @GetMapping("/details/{id}")
    public String showDetailsPage(@PathVariable("id") Integer id, Model model) {
        Cliente cliente = clienteService.findClienteById(id);
        model.addAttribute("cliente", cliente);
        return "cliente/details";
    }
}
