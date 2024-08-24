package org.esfe.services;

import org.esfe.entities.Rol;
import org.esfe.entities.Usuario;
import org.esfe.repository.RolRepository;
import org.esfe.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List; // Importa la clase List

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository; // Repositorio para gestionar roles

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Método para registrar un usuario
    public Usuario registrarUsuario(Usuario usuario) {
        usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
        return usuarioRepository.save(usuario);
    }

    // Método para buscar un usuario por correo
    public Usuario findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    // Método para guardar un rol
    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }

    // Método para obtener todos los roles
    public List<Rol> obtenerTodosLosRoles() {
        return rolRepository.findAll();
    }
}