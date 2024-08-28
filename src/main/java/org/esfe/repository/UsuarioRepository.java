package org.esfe.repository;

import org.esfe.entities.Rol;
import org.esfe.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
