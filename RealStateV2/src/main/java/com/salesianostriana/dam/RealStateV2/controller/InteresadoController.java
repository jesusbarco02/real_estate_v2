package com.salesianostriana.dam.RealStateV2.controller;


import com.salesianostriana.dam.RealStateV2.dto.interesadoDto.GetInteresadoDto;
import com.salesianostriana.dam.RealStateV2.dto.interesadoDto.GetInteresadoViviendaDto;
import com.salesianostriana.dam.RealStateV2.dto.interesadoDto.InteresadoDtoConverter;
import com.salesianostriana.dam.RealStateV2.model.Interesa;
import com.salesianostriana.dam.RealStateV2.services.InteresaService;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Rol;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import com.salesianostriana.dam.RealStateV2.usuarios.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/interesado/")
@RestController
public class InteresadoController {

    private final UsuarioService usuarioService;
    private final InteresaService interesaService;
    private final InteresadoDtoConverter interesadoDtoConverter;

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
    @GetMapping("")
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
    @GetMapping("{id}")
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

}
