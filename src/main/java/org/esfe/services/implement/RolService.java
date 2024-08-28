package org.esfe.services.implement;

import org.esfe.entities.Rol;
import org.esfe.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService implements org.esfe.interfaces.RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Page<Rol> findAll(Pageable pageable) {
        return rolRepository.findAll(pageable);
    }

    @Override
    public List<Rol> getAll() {
        return rolRepository.findAll();
    }

    @Override
    public Optional<Rol> findOneById(Integer rolID) {
        return rolRepository.findById(rolID);
    }

    @Override
    public Rol createOrEditOne(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public void deleteOneById(Integer rolID) {
        rolRepository.deleteById(rolID);
    }
}