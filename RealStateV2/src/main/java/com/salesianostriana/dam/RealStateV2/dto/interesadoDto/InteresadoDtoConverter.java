package com.salesianostriana.dam.RealStateV2.dto.interesadoDto;

import com.salesianostriana.dam.RealStateV2.model.Interesa;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class InteresadoDtoConverter {

    public GetInteresadoDto interesadoToGetInteresadoDto(Interesa i){
        return GetInteresadoDto
                .builder()
                .id(i.getId())
                .mensaje(i.getMensaje())
                .nombreUsuario(i.getUsuario().getNombre())
                .apellidosUsuario(i.getUsuario().getApellidos())
                .build();
    }



    public GetInteresadoViviendaDto interesadoToGetInteresadoViviendaDto (Usuario interesado){



        return GetInteresadoViviendaDto
                .builder()
                .id(interesado.getId())
                .nombre(interesado.getNombre())
                .apellidos(interesado.getApellidos())
                .email(interesado.getEmail())
                .telefono(interesado.getTelefono())
                .direccion(interesado.getDireccion())
                .vivienda(interesado.getInteresas().stream().map(v -> new GetInteresaDto(v.getMensaje(),v.getCreatedDate(),v.getVivienda().getTitulo(),
                        v.getVivienda().getProvincia(),v.getVivienda().getNumBanios(),
                        v.getVivienda().getNumHabitaciones(),v.getVivienda().getPrecio(),
                        v.getVivienda().getDescripcion(),v.getVivienda().getAvatar(),v.getVivienda().getInteresas())).toList())
                .build();
    }




    public GetInteresadoInteresaDto interesadoToGetInteresadoInteresaDto(Usuario interesado, Interesa interesa){

        return GetInteresadoInteresaDto
                .builder()
                .id(interesado.getId())
                .nombre(interesado.getNombre())
                .apellidos(interesado.getApellidos())
                .direccion(interesado.getDireccion())
                .email(interesado.getEmail())
                .telefono(interesado.getTelefono())
                .avatar(interesado.getAvatar())
                .mensaje(interesa.getMensaje())
                .build();
    }




}
