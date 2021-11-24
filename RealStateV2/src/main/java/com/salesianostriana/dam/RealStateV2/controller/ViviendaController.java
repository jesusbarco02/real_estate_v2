package com.salesianostriana.dam.RealStateV2.controller;

import com.salesianostriana.dam.RealStateV2.dto.viviendaDto.GetViviendaDto;
import com.salesianostriana.dam.RealStateV2.model.Vivienda;
import com.salesianostriana.dam.RealStateV2.services.ViviendaService;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Rol;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vivienda/")
public class ViviendaController {

    private final ViviendaService viviendaService;

    @Operation(summary = "Obtiene lista de viviendas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado lista de viviendas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se han encontrado las viviendas",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "No se encuentra autorizado para realizar dicha petición",
                    content = @Content),
    })
    @GetMapping("")
    public ResponseEntity<List<GetViviendaDto>> findAll(HttpServletRequest request, @AuthenticationPrincipal Usuario user) {
        List<GetViviendaDto> data = viviendaService.listarViviendasDto();
        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else if (user.getRol().equals(Rol.PROPIETARIO)){
            return ResponseEntity.ok(viviendaService.listarViviendasDto());
        }else {
            return ResponseEntity.status(403).build();
        }
    }





}
