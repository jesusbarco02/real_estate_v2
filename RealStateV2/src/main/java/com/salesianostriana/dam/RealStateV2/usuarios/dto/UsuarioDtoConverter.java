package com.salesianostriana.dam.RealStateV2.usuarios.dto;

import com.salesianostriana.dam.RealStateV2.security.dto.LoginDto;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDtoConverter {

    public GetUsuarioDto convertUsuarioToGetUsuarioDto(Usuario usuario) {
        return GetUsuarioDto.builder()
                .avatar(usuario.getAvatar())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .email(usuario.getEmail())
                .telefono(usuario.getTelefono())
                .role(usuario.getRol().name())
                .build();
    }

    public LoginDto convertUsuarioToLoginDto(Usuario usuario){
        return LoginDto.builder()
                .email(usuario.getEmail())
                .password(usuario.getPassword())
                .build();
    }


}
