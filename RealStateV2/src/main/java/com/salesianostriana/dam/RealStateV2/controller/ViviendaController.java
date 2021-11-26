package com.salesianostriana.dam.RealStateV2.controller;


import com.salesianostriana.dam.RealStateV2.dto.viviendaDto.*;
import com.salesianostriana.dam.RealStateV2.model.Inmobiliaria;
import com.salesianostriana.dam.RealStateV2.model.Vivienda;
import com.salesianostriana.dam.RealStateV2.services.InmobiliariaService;
import com.salesianostriana.dam.RealStateV2.services.ViviendaService;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Rol;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import com.salesianostriana.dam.RealStateV2.usuarios.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ViviendaController {

    private final ViviendaService viviendaService;
    private final ViviendaDtoConverter viviendaDtoConverter;
    private final UsuarioService usuarioService;
    private final InmobiliariaService inmobiliariaService;


    @Operation(summary = "Obtiene lista de viviendas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado lista de viviendas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se han encontrado las viviendas",
                    content = @Content),
    })
    @GetMapping("/vivienda/")
    public ResponseEntity<List<GetViviendaDto>> findAll(HttpServletRequest request, @AuthenticationPrincipal Usuario user) {
        List<GetViviendaDto> data = viviendaService.listarViviendasDto();
        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(viviendaService.listarViviendasDto());
        }
    }


    @Operation(summary = "Obtiene los detalles de una vivienda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado la vivienda indicada por ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se han encontrado la vivienda indicada por ID",
                    content = @Content)
    })
    @GetMapping("/vivienda/{id}")
    public ResponseEntity<List<GetViviendaPropietarioDto>> findOnePropietario(@PathVariable Long id) {
        Optional<Vivienda> data = viviendaService.findById(id);

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<GetViviendaPropietarioDto> viviendaDto = data
                    .stream().map(viviendaDtoConverter :: viviendaToGetViviendaPropietarioDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(viviendaDto);
        }
    }


    @Operation(summary = "Crea una nueva vivienda y añade una inmobiliaria ya existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado la nueva vivienda",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado la nueva vivienda",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No se encuentra autorizado",
                    content = @Content),
    })
    @PostMapping("/vivienda/{id}/inmobiliaria/{id2}")
    public ResponseEntity<GetViviendaInmobiliariaDto> createViviendaInmobiliaria (@PathVariable Long id, @PathVariable Long id2, @AuthenticationPrincipal Usuario user){
        Optional<Vivienda> vivienda = viviendaService.findById(id);
        Optional<Inmobiliaria> inmobiliaria = inmobiliariaService.findById(id2);
        Inmobiliaria inmobiliaria1 = inmobiliaria.get();
        Vivienda vivienda1 = vivienda.get();
        if (vivienda.isEmpty()) {
            return  ResponseEntity.notFound().build();
        }else if (user.getRol().equals(Rol.ADMIN) || (user.getRol().equals(Rol.PROPIETARIO) && vivienda.get().getUsuario().getId().equals(user.getId())))  {
            vivienda1.setInmobiliaria(inmobiliaria1);
            viviendaService.save(vivienda1);
            GetViviendaInmobiliariaDto getViviendaDto = viviendaDtoConverter.viviendaToGetViviendaInmobiliariaDto(vivienda1);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(getViviendaDto);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }


    @Operation(summary = "Crea una nueva vivienda a la que se asocia un  propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado la nueva vivienda",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado el propietario",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado la nueva vivienda",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No se encuentra autorizado",
                    content = @Content),
    })
    @PostMapping("/vivienda/")
    public ResponseEntity<GetViviendaPropietarioDto> create(@RequestBody CreateViviendaDto dto, @AuthenticationPrincipal Usuario user) {

        if (dto.getPropietarioId() == null) {
            return ResponseEntity.badRequest().build();
        }else if (user.getRol().equals(Rol.PROPIETARIO)){
            Vivienda nueva = viviendaDtoConverter.createViviendaDtoToVivienda(dto);
            Usuario propietario = usuarioService.findById(user.getId()).orElse(null);
            nueva.setUsuario(propietario);
            viviendaService.save(nueva);
            GetViviendaPropietarioDto converter = viviendaDtoConverter.viviendaToGetViviendaPropietarioDto(nueva);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(converter);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    @Operation(summary = "Edita una vivienda anteriormente creada, buscando por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado la vivienda",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha editado la vivienda",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No se encuentra autorizado",
                    content = @Content),
    })
    @PutMapping("/vivienda/{id}")
    public ResponseEntity<PutViviendaDto> edit (@RequestBody PutViviendaDto v, @PathVariable Long id, @AuthenticationPrincipal Usuario user) {
        Optional<Vivienda> vivienda= viviendaService.findById(id);

        if (vivienda.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else if(vivienda.get().getUsuario().getId().equals(user.getId())) {

            return ResponseEntity.of(
                    vivienda.map(m -> {
                        m.setTitulo(v.getTitulo());
                        m.setProvincia(v.getProvincia());
                        m.setTipoVivienda(v.getTipoVivienda());
                        m.setNumBanios(v.getNumBanios());
                        m.setNumHabitaciones(v.getNumHabitaciones());
                        m.setMetrosCuadrados(v.getMetrosCuadrados());
                        m.setPrecio(v.getPrecio());
                        m.setDescripcion(v.getDescripcion());
                        m.setAvatar(v.getAvatar());
                        m.setDireccion(v.getDireccion());
                        m.setCodigoPostal(v.getCodigoPostal());
                        m.setLatlng(v.getLatlng());
                        m.setPoblacion(v.getPoblacion());
                        m.setTienePiscina(v.isTienePiscina());
                        m.setTieneAscensor(v.isTieneAscensor());
                        m.setTieneGaraje(v.isTieneGaraje());
                        m.setUsuario(m.getUsuario());
                        viviendaService.save(m);
                        return viviendaDtoConverter.editViviendaDtoToVivienda(m);
                    })
            );
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }
    @Operation(summary = "Se elimina la vivienda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la vivienda",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha borrado la vivienda",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes autorización",
                    content = @Content),
    })
    @DeleteMapping("/vivienda/{id}")
    public ResponseEntity<?> deletePropietario(@PathVariable Long id, HttpServletRequest request, @AuthenticationPrincipal Usuario user) {

        Optional<Vivienda> vivienda = viviendaService.loadUserById(id);
        if (vivienda.isEmpty()){
            return ResponseEntity.notFound().build();
        }else if (user.getRol().equals(Rol.ADMIN) || (user.getRol().equals(Rol.PROPIETARIO) && vivienda.get().getUsuario().getId().equals(user.getId()))) {
            viviendaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Operation(summary = "Se elimina la inmobiliaria sobre la vivienda especificada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la inmobiliaria",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha borrado la inmobiliaria",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No se encuentra autorizado",
                    content = @Content),
    })
    @DeleteMapping("/vivienda/{id}/inmobiliaria")
    public ResponseEntity<?> deleteInmobiliaria (@PathVariable Long id, @AuthenticationPrincipal Usuario user){
        Optional <Vivienda> vivienda = viviendaService.findById(id);

        if (vivienda.isEmpty()) {
            return ResponseEntity.noContent().build();
        }  else if (user.getRol().equals(Rol.ADMIN) || (user.getRol().equals(Rol.PROPIETARIO) && vivienda.get().getUsuario().getId().equals(user.getId())) ||
                (user.getRol().equals(Rol.GESTOR)&& vivienda.get().getInmobiliaria().getId().equals(user.getInmobiliaria().getId()))) {
            vivienda.map(v -> {
                v.setInmobiliaria(null);
                viviendaService.save(v);
                return ResponseEntity.noContent().build();

            });
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
