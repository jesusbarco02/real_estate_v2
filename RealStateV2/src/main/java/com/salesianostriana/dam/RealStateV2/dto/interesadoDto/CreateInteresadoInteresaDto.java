package com.salesianostriana.dam.RealStateV2.dto.interesadoDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInteresadoInteresaDto {

    private String nombre, apellidos, direccion, email, telefono, avatar;
    private LocalDate createdDate;
    private String mensaje;
    private Long viviendaId;
}
