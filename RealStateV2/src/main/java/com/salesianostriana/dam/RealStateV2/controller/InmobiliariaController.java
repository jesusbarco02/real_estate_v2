package com.salesianostriana.dam.RealStateV2.controller;

import com.salesianostriana.dam.RealStateV2.dto.inmobiliariaDto.CreateInmobiliariaDto;
import com.salesianostriana.dam.RealStateV2.dto.inmobiliariaDto.GetInmobiliariaDto;
import com.salesianostriana.dam.RealStateV2.dto.inmobiliariaDto.InmobiliariaDtoConverter;
import com.salesianostriana.dam.RealStateV2.model.Inmobiliaria;
import com.salesianostriana.dam.RealStateV2.services.InmobiliariaService;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Rol;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/inmobiliaria/")
@RequiredArgsConstructor
public class InmobiliariaController {

    private final InmobiliariaService inmobiliariaService;
    private final InmobiliariaDtoConverter inmobiliariaDtoConverter;

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

}
