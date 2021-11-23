package com.salesianostriana.dam.RealStateV2.usuarios.repos;

import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Rol;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findFirstByEmail(String email);
    List<Usuario> findByRol (Rol rol);
}
