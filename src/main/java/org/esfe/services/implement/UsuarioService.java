package org.esfe.services.implement;

import org.esfe.entities.Usuario;
import org.esfe.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements org.esfe.interfaces.UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public  Page<Usuario> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable); // Usar la instancia de usuarioRepository
    }

    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll(); // Usar la instancia de usuarioRepository
    }

    @Override
    public Optional<Usuario> findOneById(Integer usuarioId) {
        return usuarioRepository.findById(usuarioId); // Usar la instancia de usuarioRepository
    }

    @Override
    public Usuario createOrEditOne(Usuario usuario) {
        return usuarioRepository.save(usuario); // Usar la instancia de usuarioRepository
    }

    @Override
    public void deleteOneById(Integer usuarioId) {
        usuarioRepository.deleteById(usuarioId); // Usar la instancia de usuarioRepository
    }
}