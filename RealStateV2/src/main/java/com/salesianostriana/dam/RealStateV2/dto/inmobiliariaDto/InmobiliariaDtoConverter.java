package com.salesianostriana.dam.RealStateV2.dto.inmobiliariaDto;

import com.salesianostriana.dam.RealStateV2.dto.viviendaDto.GetViviendaDto;
import com.salesianostriana.dam.RealStateV2.model.Inmobiliaria;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public GetInmobiliariaDto inmobiliariaToGetInmobiliariaDetallesDto(Inmobiliaria i){
        GetInmobiliariaDto result = new GetInmobiliariaDto();
        result.setId(i.getId());
        result.setNombre(i.getNombre());
        result.setEmail(i.getEmail());
        result.setTelefono(i.getTelefono());
        return result;
    }

    public GetInmobiliariaGestorDto inmobiliariaToGetInmobiliariaViviendasDtoPost(Usuario u, Inmobiliaria i){

        List<String> nombresGestores = new ArrayList<>();
        for (int j = 0; j < i.getUsuarios().size(); j++){
            nombresGestores.add(i.getUsuarios().get(j).getNombre());
        }
        return GetInmobiliariaGestorDto.builder()
                .id(i.getId())
                .nombre(i.getNombre())
                .idGestor(u.getId())
                .build();
    }
}
