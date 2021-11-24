package com.salesianostriana.dam.RealStateV2.dto.viviendaDto;


import com.salesianostriana.dam.RealStateV2.model.Vivienda;
import org.springframework.stereotype.Component;

@Component
public class ViviendaDtoConverter {

        public GetViviendaPropietarioDto viviendaToGetViviendaPropietarioDto (Vivienda v) {

            return GetViviendaPropietarioDto
                    .builder()
                    .id(v.getId())
                    .titulo(v.getTitulo())
                    .provincia(v.getProvincia())
                    .numBanios(v.getNumBanios())
                    .numHabitaciones(v.getNumHabitaciones())
                    .metrosCuadrados(v.getMetrosCuadrados())
                    .precio(v.getPrecio())
                    .descripcion(v.getDescripcion())
                    .avatar(v.getAvatar())
                    .avatar(v.getAvatar())
                    .nombrePropietario(v.getUsuario().getNombre())
                    .apellidosPropietario(v.getUsuario().getApellidos())
                    .telefonoPropietario(v.getUsuario().getTelefono())
                    .direccionPropietario(v.getUsuario().getDireccion())
                    .emailPropietario(v.getUsuario().getEmail())
                    .build();
        }

    public CreateViviendaDto createViviendaDtoToVivienda(CreateViviendaDto v){

        return CreateViviendaDto
                .builder()
                .titulo(v.getTitulo())
                .provincia(v.getProvincia())
                .tipoVivienda(v.getTipoVivienda())
                .numBanios(v.getNumBanios())
                .numHabitaciones(v.getNumHabitaciones())
                .metrosCuadrados(v.getMetrosCuadrados())
                .precio(v.getPrecio())
                .descripcion(v.getDescripcion())
                .codigoPostal(v.getCodigoPostal())
                .latlng(v.getLatlng())
                .poblacion(v.getPoblacion())
                .tienePiscina(v.isTienePiscina())
                .tieneAscensor(v.isTieneAscensor())
                .tieneGaraje(v.isTieneGaraje())
                .propietarioId(v.getPropietarioId())
                .build();
    }
}
