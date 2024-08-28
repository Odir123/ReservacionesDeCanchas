package org.esfe.interfaces;

import org.esfe.entities.Cancha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CanchaService {

    Page<Cancha> buscarTodosPaginados(Pageable pageable);

    List<Cancha> obtenerTodos();

    Optional<Cancha> buscarPorId(Integer id);

    Cancha crearOEditar(Cancha cancha);

    void eliminarPorId(Integer id);
}
