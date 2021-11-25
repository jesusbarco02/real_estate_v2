package com.salesianostriana.dam.RealStateV2.dto.propietarioDto;

import com.salesianostriana.dam.RealStateV2.dto.viviendaDto.GetViviendaDto;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class PropietarioDtoConverter {
    public GetPropietarioViviendaDto propietarioToGetPropietarioViviendaDto (Usuario p) {

        return GetPropietarioViviendaDto
                .builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .apellidos(p.getApellidos())
                .direccion(p.getDireccion())
                .email(p.getEmail())
                .avatar(p.getAvatar())
                .telefono(p.getTelefono())
                .viviendas(p.getListaViviendas().stream().map(v -> new GetViviendaDto(v.getId(),v.getTitulo()
                        ,v.getProvincia(),
                        v.getNumBanios(), v.getNumHabitaciones(),v.getMetrosCuadrados(),v.getPrecio()
                        ,v.getDescripcion(),v.getAvatar())).toList())
                .rol(p.getRol())
                .build();
    }
    public GetPropietarioDto propietarioToGetPropietarioDto (Usuario p) {
        int numVivienda= p.getListaViviendas().size();
        return GetPropietarioDto
                .builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .apellidos(p.getApellidos())
                .direccion(p.getDireccion())
                .email(p.getEmail())
                .telefono(p.getTelefono())
                .numViviendas(numVivienda)
                .build();
    }


}
