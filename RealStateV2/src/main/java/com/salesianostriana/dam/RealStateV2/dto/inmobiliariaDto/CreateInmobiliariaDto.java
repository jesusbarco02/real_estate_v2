package com.salesianostriana.dam.RealStateV2.dto.inmobiliariaDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInmobiliariaDto {

    private String nombre, email, telefono;

}
