package com.salesianostriana.dam.RealStateV2.dto.inmobiliariaDto;

import com.salesianostriana.dam.RealStateV2.dto.viviendaDto.GetViviendaDto;
import com.salesianostriana.dam.RealStateV2.model.Inmobiliaria;
import org.springframework.stereotype.Component;

@Component
public class InmobiliariaDtoConverter {

    public GetInmobiliariaDto inmobiliariaToGetInmobiliariaDto (Inmobiliaria i) {

        return GetInmobiliariaDto
                .builder()
                .id(i.getId())
                .nombre(i.getNombre())
                .email(i.getEmail())
                .telefono(i.getTelefono())
                .build();

    }

    public Inmobiliaria createInmpbiliariaDtoToInmobiliaria (CreateInmobiliariaDto i) {
        return new Inmobiliaria(
                i.getNombre(),
                i.getEmail(),
                i.getTelefono()
        );

    }

    public GetInmobiliariaViviendaDto inmobiliariaToGetInmobiliariaViviendaDto (Inmobiliaria i) {

        return GetInmobiliariaViviendaDto
                .builder()
                .id(i.getId())
                .nombre(i.getNombre())
                .email(i.getEmail())
                .telefono(i.getTelefono())
                .viviendas(i.getViviendas().stream().map(v -> new GetViviendaDto(v.getId(),v.getTitulo()
                        ,v.getProvincia(),
                        v.getNumBanios(), v.getNumHabitaciones(),v.getMetrosCuadrados(),v.getPrecio()
                        ,v.getDescripcion(),v.getAvatar())).toList())
                .build();

    }
}
