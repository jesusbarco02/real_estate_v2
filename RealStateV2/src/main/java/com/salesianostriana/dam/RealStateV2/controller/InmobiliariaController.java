package com.salesianostriana.dam.RealStateV2.controller;

import com.salesianostriana.dam.RealStateV2.dto.inmobiliariaDto.CreateInmobiliariaDto;
import com.salesianostriana.dam.RealStateV2.dto.inmobiliariaDto.GetInmobiliariaDto;
import com.salesianostriana.dam.RealStateV2.dto.inmobiliariaDto.GetInmobiliariaViviendaDto;
import com.salesianostriana.dam.RealStateV2.dto.inmobiliariaDto.InmobiliariaDtoConverter;
import com.salesianostriana.dam.RealStateV2.model.Inmobiliaria;
import com.salesianostriana.dam.RealStateV2.model.Vivienda;
import com.salesianostriana.dam.RealStateV2.services.InmobiliariaService;
import com.salesianostriana.dam.RealStateV2.services.ViviendaService;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Rol;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/inmobiliaria/")
@RequiredArgsConstructor
public class InmobiliariaController {

    private final InmobiliariaService inmobiliariaService;
    private final InmobiliariaDtoConverter inmobiliariaDtoConverter;
    private final ViviendaService viviendaService;

    @Operation(summary = "Muestra una lista de todas las inmobiliarias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las inmobiliarias",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "No se han encontrado las inmobiliarias",
                    content = @Content),
    })
    @GetMapping("")
    public ResponseEntity<List<GetInmobiliariaDto>> findAll() {

        List<Inmobiliaria> data = inmobiliariaService.findAll();

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<GetInmobiliariaDto> result = data.stream().map(inmobiliariaDtoConverter::inmobiliariaToGetInmobiliariaDto).collect(Collectors.toList());
            return ResponseEntity.ok().body(result);
        }
    }


    @Operation(summary = "Crea una nueva inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han creado la inmobiliaria",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado la inmobiliaria",
                    content = @Content),
    })
    @PostMapping("")
    public ResponseEntity<Inmobiliaria> create(@RequestBody CreateInmobiliariaDto dto, @AuthenticationPrincipal Usuario user){
        if (dto.getNombre().isEmpty()){
            return ResponseEntity.notFound().build();
        }else if (user.getRol().equals(Rol.ADMIN)) {

            Inmobiliaria nueva = inmobiliariaDtoConverter.createInmpbiliariaDtoToInmobiliaria(dto);

            return ResponseEntity.status(HttpStatus.CREATED).body(inmobiliariaService.save(nueva));

        }else {
            return ResponseEntity.status(403).build();
        }

    }

    @Operation(summary = "Muestra una inmobiliaria y sus viviendas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado la inmobiliaria",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado la inmobiliaria",
                    content = @Content),
    })
    @GetMapping("{id}")
    public ResponseEntity<List<GetInmobiliariaViviendaDto>> findOne(@PathVariable Long id){

        Optional<Inmobiliaria> inmobiliaria = inmobiliariaService.findById(id);

        if (inmobiliaria.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            List<GetInmobiliariaViviendaDto> result =
                    inmobiliaria.stream()
                            .map(inmobiliariaDtoConverter::inmobiliariaToGetInmobiliariaViviendaDto)
                            .collect(Collectors.toList());
            return ResponseEntity.ok().body(result);
        }
    }


    @Operation(summary = "Borra una inmobiliaria en base a su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la inmobiliaria",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha borrado la inmobiliaria",
                    content = @Content),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteInmobiliaria(@PathVariable Long id) {
        Optional<Inmobiliaria> inmobiliaria = inmobiliariaService.findById(id);
        Inmobiliaria inmobiliaria1 = inmobiliaria.get();

        if (inmobiliaria.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            inmobiliaria1.preRemove();
            inmobiliariaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }



}
