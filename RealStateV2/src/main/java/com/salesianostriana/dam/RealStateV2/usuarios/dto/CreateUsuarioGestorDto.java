package com.salesianostriana.dam.RealStateV2.usuarios.dto;

import com.salesianostriana.dam.RealStateV2.model.Inmobiliaria;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUsuarioGestorDto {

    private String nombre;
    private String avatar;
    private String apellidos;
    private String direccion;
    private String email;
    private String password;
    private String password2;
    private String telefono;
    private Long inmobiliaria;
}
