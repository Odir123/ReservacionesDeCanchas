package org.esfe.repository;

import org.esfe.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByEstado(String estado);
    List<Reserva> findByClienteId(Integer clienteId);
    List<Reserva> findByCanchaId(Integer canchaId);
    List<Reserva> findByUsuarioId(Integer usuarioId);
}
