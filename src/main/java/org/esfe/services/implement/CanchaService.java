package org.esfe.services.implement;

import org.esfe.entities.Cancha;
import org.esfe.repository.CanchaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CanchaService implements org.esfe.interfaces.CanchaService {

    @Autowired
    private CanchaRepository canchaRepository;

    @Override
    public Page<Cancha> findAll(Pageable pageable) {
        return canchaRepository.findAll(pageable);
    }

    @Override
    public List<Cancha> getAll() {
        return canchaRepository.findAll();
    }

    @Override
    public Optional<Cancha> findOneById(Long canchaId) {  // Cambié Integer a Long para coincidir con la entidad `Cancha`
        return canchaRepository.findById(canchaId);
    }

    @Override
    public Cancha createOrEditOne(Cancha cancha) {
        return canchaRepository.save(cancha);
    }

    @Override
    public void deleteOneById(Long canchaId) {  // Cambié Integer a Long para coincidir con la entidad `Cancha`
        canchaRepository.deleteById(canchaId);
    }
}










