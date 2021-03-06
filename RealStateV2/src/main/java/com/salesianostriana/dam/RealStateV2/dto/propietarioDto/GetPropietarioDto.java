package com.salesianostriana.dam.RealStateV2.dto.propietarioDto;

import com.salesianostriana.dam.RealStateV2.dto.viviendaDto.GetViviendaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPropietarioDto {

    private Long id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String email;
    private String telefono;
    private int numViviendas;
}
