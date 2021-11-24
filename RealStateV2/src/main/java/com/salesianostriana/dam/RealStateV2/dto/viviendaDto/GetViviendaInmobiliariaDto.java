package com.salesianostriana.dam.RealStateV2.dto.viviendaDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetViviendaInmobiliariaDto {
    private Long id;
    private String titulo;
    private String provincia;
    private int numBanios;
    private int numHabitaciones;
    private double metrosCuadrados;
    private double precio;
    private String descripcion;
    private String avatar;
    private Long inmobiliariaId;
}
