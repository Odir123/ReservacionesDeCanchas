package org.esfe.interfaces;

import org.esfe.entities.Cancha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CanchaService {

    Page<Cancha> findAll(Pageable pageable);

    List<Cancha> getAll();

    Optional<Cancha> findOneById(Long canchaId);  // Cambié Integer a Long para coincidir con la entidad `Cancha`

    Cancha createOrEditOne(Cancha cancha);

    void deleteOneById(Long canchaId);  // Cambié Integer a Long para coincidir con la entidad `Cancha`
}
