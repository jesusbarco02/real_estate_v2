package com.salesianostriana.dam.RealStateV2.dto.viviendaDto;


import com.salesianostriana.dam.RealStateV2.model.TipoVivienda;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateViviendaDto {

    private String titulo;
    private String provincia;
    private TipoVivienda tipoVivienda;
    private int numBanios;
    private int numHabitaciones;
    private double metrosCuadrados;
    private double precio;
    private String descripcion;
    private String avatar;
    private String direccion;
    private String codigoPostal;
    private String latlng;
    private String poblacion;
    private boolean tienePiscina;
    private boolean tieneAscensor;
    private boolean tieneGaraje;
    private Long propietarioId;
}
