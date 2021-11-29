package com.salesianostriana.dam.RealStateV2.security.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtUsuarioResponse { // TODO Este DTO se podría mejorar para que cumpliera las necesidades del proyecto

    private String email;
    private String nombre;
    private String apellidos;
    private String avatar;
    private String role;
    private String token;
}
