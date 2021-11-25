package com.salesianostriana.dam.RealStateV2.dto.inmobiliariaDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetInmobiliariaGestorDto {

    private Long id;
    private String nombre;
    private Long idGestor;

}
