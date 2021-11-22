package com.salesianostriana.dam.RealStateV2.usuarios.repos;

import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findFirstByEmail(String email);

}
