package com.salesianostriana.dam.RealStateV2.dto.inmobiliariaDto;

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

    public Inmobiliaria createInmpbiliariaDtoToInmobiliaria (CreateInmobiliariaDto i){
        return new Inmobiliaria(
                i.getNombre(),
                i.getEmail(),
                i.getTelefono()
        );



    }
}
