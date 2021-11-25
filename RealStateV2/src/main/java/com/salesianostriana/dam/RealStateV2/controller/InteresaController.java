package com.salesianostriana.dam.RealStateV2.controller;


import com.salesianostriana.dam.RealStateV2.dto.interesadoDto.*;
import com.salesianostriana.dam.RealStateV2.model.Interesa;
import com.salesianostriana.dam.RealStateV2.model.Vivienda;
import com.salesianostriana.dam.RealStateV2.services.InteresaService;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class InteresaController {

    private final UsuarioService usuarioService;
    private final InteresaService interesaService;
    private final InteresadoDtoConverter interesadoDtoConverter;
    private final ViviendaService viviendaService;

    @Operation(summary = "Obtiene todos los interesados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han listado todos los interesados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se han listado los interesados",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "No tienes autorización",
                    content = @Content),
    })
    @GetMapping("/interesado/")
    public ResponseEntity<List<GetInteresadoDto>> findAll(@AuthenticationPrincipal Usuario user){

        List<Interesa> data = interesaService.findAll();

        if (data.isEmpty()){
            return ResponseEntity.notFound().build();
        }else if(user.getRol().equals(Rol.ADMIN)){
            List<GetInteresadoDto> result =
                    data.stream()
                            .map(interesadoDtoConverter::interesadoToGetInteresadoDto)
                            .collect(Collectors.toList());
            return ResponseEntity.ok().body(result);
        }else {
            return ResponseEntity.status(403).build();
        }
    }

    @Operation(summary = "Obtiene el interesado que le indicamos por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el interesado especificado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado el interesado",
                    content = @Content),
    })
    @GetMapping("/interesado/{id}")
    public ResponseEntity<List<GetInteresadoViviendaDto>> findOne(@PathVariable Long id, @AuthenticationPrincipal Usuario user){
        Optional<Usuario> data = usuarioService.findById(id);

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else if(user.getRol().equals(Rol.ADMIN) || (data.equals(user.getId()) )) {
            List<GetInteresadoViviendaDto> interesadoDto = data
                    .stream().map(interesadoDtoConverter :: interesadoToGetInteresadoViviendaDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(interesadoDto);
        }else {
            return ResponseEntity.status(403).build();
        }
    }



    @Operation(summary = "Crea un interesado, y a la vez añade un interesado por una vivienda ya creada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado el nuevo interesado con interés",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se encuentra la vivienda",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido crear el interesado o hay datos erróneos",
                    content = @Content)
    })
    @PostMapping("/vivienda/{id}/meinteresa")
    public ResponseEntity<GetInteresadoInteresaDto> create(@PathVariable("id") Long id, @RequestBody CreateInteresadoInteresaDto dto,
                                                           @AuthenticationPrincipal Usuario user){

        if (viviendaService.findById(id).isEmpty()){
            return  ResponseEntity.notFound().build();
        }else if (user.getRol().equals(Rol.PROPIETARIO)){
            Optional<Vivienda> v = viviendaService.findById(id);
            Usuario interesado = interesadoDtoConverter.createInteresadoDtoToInteresado(dto);
            Interesa interesa = Interesa.builder()
                    .mensaje(dto.getMensaje())
                    .build();
            interesa.addToUsuario(interesado);
            interesa.addToVivienda(v.get());
            usuarioService.save(interesado);
            interesaService.save(interesa);
            GetInteresadoInteresaDto interesadoInteresaDto = interesadoDtoConverter.
                    interesadoToGetInteresadoInteresaDto(interesado, interesa);
            return ResponseEntity.status(HttpStatus.CREATED).body(interesadoInteresaDto);
        }else {
            return ResponseEntity.status(403).build();
        }
    }

    @DeleteMapping("/vivienda/{id}/meinteresa")
    public ResponseEntity<?> deleteInmobiliaria (@PathVariable Long id, @AuthenticationPrincipal Usuario user){
        Optional <Vivienda> vivienda = viviendaService.findById(id);

        if (vivienda.isEmpty()) {
            return ResponseEntity.noContent().build();
        }  else if (user.getRol().equals(Rol.ADMIN) || (user.getRol().equals(Rol.PROPIETARIO) && vivienda.get().getInteresas().equals(user.getId()))) {
            vivienda.map(v -> {
                v.setInteresas(null);
                viviendaService.save(v);
                return ResponseEntity.noContent().build();

            });
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.status(403).build();
        }
    }



}
