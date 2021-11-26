package com.salesianostriana.dam.RealStateV2.dto.viviendaDto;


import com.salesianostriana.dam.RealStateV2.model.Vivienda;
import com.salesianostriana.dam.RealStateV2.usuarios.services.UsuarioService;
import org.springframework.stereotype.Component;

@Component
public class ViviendaDtoConverter {

    private final UsuarioService propietarioService;

    public ViviendaDtoConverter(UsuarioService propietarioService) {
        this.propietarioService = propietarioService;
    }


    public Vivienda createViviendaDtoToVivienda(CreateViviendaDto v){

        return Vivienda.builder()
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
                .usuario(propietarioService.findById(v.getPropietarioId()).get())
                .direccion(v.getDireccion())
                .latlng(v.getLatlng())
                .tipoVivienda(v.getTipoVivienda())
                .avatar(v.getAvatar())
                .build();
    }
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

    public PutViviendaDto editViviendaDtoToVivienda (Vivienda v) {
        return new PutViviendaDto(
                v.getTitulo(),
                v.getProvincia(),
                v.getTipoVivienda(),
                v.getNumBanios(),
                v.getNumHabitaciones(),
                v.getMetrosCuadrados(),
                v.getPrecio(),
                v.getDescripcion(),
                v.getAvatar(),
                v.getDireccion(),
                v.getCodigoPostal(),
                v.getLatlng(),
                v.getPoblacion(),
                v.isTienePiscina(),
                v.isTieneAscensor(),
                v.isTieneGaraje()

        );
    }




}
