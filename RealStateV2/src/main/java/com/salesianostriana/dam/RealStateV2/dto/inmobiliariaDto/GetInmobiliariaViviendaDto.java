package com.salesianostriana.dam.RealStateV2.dto.inmobiliariaDto;

import com.salesianostriana.dam.RealStateV2.dto.viviendaDto.GetViviendaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetInmobiliariaViviendaDto {

    private Long id;
    private String nombre, email, telefono;
    private List<GetViviendaDto> viviendas;


}
