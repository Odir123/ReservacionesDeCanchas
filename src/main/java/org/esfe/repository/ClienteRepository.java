package org.esfe.repository;

import org.esfe.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByCorreo(String correo);
    Cliente findByDui(String dui);
}
