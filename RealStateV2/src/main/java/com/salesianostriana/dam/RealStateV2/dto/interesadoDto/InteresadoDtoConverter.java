package com.salesianostriana.dam.RealStateV2.dto.interesadoDto;

import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class InteresadoDtoConverter {

    public GetInteresadoDto interesadoToGetInteresadoDto(Usuario i){
        return GetInteresadoDto
                .builder()
                .id(i.getId())
                .nombre(i.getNombre())
                .apellidos(i.getApellidos())
                .direccion(i.getDireccion())
                .email(i.getEmail())
                .telefono(i.getTelefono())
                .avatar(i.getAvatar())
                .build();
    }
}
