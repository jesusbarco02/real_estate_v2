package com.salesianostriana.dam.RealStateV2.usuarios.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUsuarioDto {

    private String nombre;
    private String avatar;
    private String apellidos;
    private String direccion;
    private String email;
    private String password;
    private String password2;
    private String telefono;
}
