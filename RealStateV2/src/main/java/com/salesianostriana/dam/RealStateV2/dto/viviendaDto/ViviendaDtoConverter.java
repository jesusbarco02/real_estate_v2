package com.salesianostriana.dam.RealStateV2.dto.viviendaDto;


import com.salesianostriana.dam.RealStateV2.dto.inmobiliariaDto.InmobiliariaDtoConverter;
import com.salesianostriana.dam.RealStateV2.dto.interesadoDto.InteresadoDtoConverter;
import com.salesianostriana.dam.RealStateV2.dto.propietarioDto.PropietarioDtoConverter;
import com.salesianostriana.dam.RealStateV2.model.Vivienda;
import org.springframework.stereotype.Component;

@Component
public class ViviendaDtoConverter {

    private final InmobiliariaDtoConverter inmobiliariaDtoConverter= new InmobiliariaDtoConverter();
    private final PropietarioDtoConverter propietarioDtoConverter = new PropietarioDtoConverter();
    private final InteresadoDtoConverter interesadoDtoConverter = new InteresadoDtoConverter();

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

    public GetViviendaInmobiliariaDto viviendaToGetViviendaInmobiliariaDto (Vivienda v) {

        return GetViviendaInmobiliariaDto
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
                .inmobiliariaId(v.getInmobiliaria().getId())
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
