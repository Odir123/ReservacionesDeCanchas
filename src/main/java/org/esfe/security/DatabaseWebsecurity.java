package org.esfe.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DatabaseWebsecurity {

    @Bean
    public UserDetailsManager customUsers(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        // Query para obtener los detalles del usuario (nombre, contraseña y estado)
        users.setUsersByUsernameQuery(
                "SELECT nombre AS username, contrasenia AS password, 1 AS enabled FROM usuario WHERE nombre = ?"
        );

        // Query para obtener los roles asociados con el usuario
        users.setAuthoritiesByUsernameQuery(
                "SELECT u.nombre AS username, r.nombre AS authority " +
                        "FROM usuario_rol ur " +
                        "INNER JOIN usuario u ON u.id = ur.usuario_id " +
                        "INNER JOIN rol r ON r.id = ur.rol_id " +
                        "WHERE u.nombre = ?"
        );

        return users;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                // Permitir acceso a recursos estáticos
                .requestMatchers("/assets/**", "/css/**", "/js/**").permitAll()
                // Permitir vistas públicas
                .requestMatchers("/", "/privacy", "/terms").permitAll()
                // Permitir acceso a todas las demás vistas sin autenticación
                .anyRequest().permitAll()
        );
        // Opcionalmente puedes habilitar el formulario de login:
        http.formLogin(form -> form.permitAll());

        return http.build();
    }
}




