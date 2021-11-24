package com.salesianostriana.dam.RealStateV2.dto.inmobiliariaDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetInmobiliariaDto {


    private Long id;
    private String nombre, email, telefono;

}
