package org.esfe.services;
import org.esfe.entities.Cliente;
import org.esfe.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    // Method to find all clientes
    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    // Method to get pagination numbers (example implementation)
    public List<Integer> getPageNumbers() {
        // Replace with actual pagination logic if needed
        return List.of(1, 2, 3, 4); // Example page numbers
    }

    // Method to find cliente by correo
    public Cliente findByCorreo(String correo) {
        return clienteRepository.findByCorreo(correo);
    }

    // Method to find cliente by dui
    public Cliente findByDui(String dui) {
        return clienteRepository.findByDui(dui);
    }

    // Method to find cliente by ID
    public Cliente findClienteById(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new RuntimeException("Cliente not found for id: " + id);
        }
    }

    // Method to save cliente
    public void saveCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    // Method to update cliente
    public void updateCliente(Cliente cliente) {
        if (clienteRepository.existsById(cliente.getId())) {
            clienteRepository.save(cliente);
        } else {
            throw new RuntimeException("Cliente not found for id: " + cliente.getId());
        }
    }

    // Method to delete cliente by ID
    public void deleteCliente(Integer id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cliente not found for id: " + id);
        }
    }
}
