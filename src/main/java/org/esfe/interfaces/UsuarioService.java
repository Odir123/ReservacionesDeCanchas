package org.esfe.interfaces;

import org.esfe.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Page<Usuario> findAll(Pageable pageable);

    List<Usuario> getAll();

    Optional<Usuario> findOneById(Integer usuarioId);

    Usuario createOrEditOne(Usuario usuario);

    void deleteOneById(Integer usuarioId);

}
