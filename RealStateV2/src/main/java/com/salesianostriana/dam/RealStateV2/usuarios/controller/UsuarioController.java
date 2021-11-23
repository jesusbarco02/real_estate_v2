package com.salesianostriana.dam.RealStateV2.usuarios.controller;

import com.salesianostriana.dam.RealStateV2.usuarios.dto.CreateUsuarioDto;
import com.salesianostriana.dam.RealStateV2.usuarios.dto.CreateUsuarioGestorDto;
import com.salesianostriana.dam.RealStateV2.usuarios.dto.GetUsuarioDto;
import com.salesianostriana.dam.RealStateV2.usuarios.dto.UsuarioDtoConverter;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Rol;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import com.salesianostriana.dam.RealStateV2.usuarios.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioDtoConverter usuarioDtoConverter;

    @Operation(summary = "Crea un nuevo usuario propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado el usuario propietario",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado el usuario propietario",
                    content = @Content),
    })
    @PostMapping("/auth/register/user")
    public ResponseEntity<GetUsuarioDto> nuevoUsuario(@RequestBody CreateUsuarioDto usuarioPropietario) {
        Usuario saved = usuarioService.savePropietario(usuarioPropietario);

        if (saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(usuarioDtoConverter.convertUsuarioToGetUsuarioDto(saved));

    }

    @Operation(summary = "Crea un nuevo usuario gestor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado el usuario gestor",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado el usuario gestor",
                    content = @Content),
    })
    @PostMapping("/auth/register/gestor")
    public ResponseEntity<GetUsuarioDto> nuevoUsuarioGestor(@RequestBody CreateUsuarioGestorDto usuarioGestorDto) {
        Usuario saved = usuarioService.saveGestor(usuarioGestorDto);

        if (saved == null || saved.getInmobiliaria()== null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(usuarioDtoConverter.convertUsuarioToGetUsuarioDto(saved));

    }
    @Operation(summary = "Crea un nuevo usuario admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado el usuario admin",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado el usuario admin",
                    content = @Content),
    })
    @PostMapping("/auth/register/admin")
    public ResponseEntity<GetUsuarioDto> nuevoUsuarioGestor(@RequestBody CreateUsuarioDto usuarioAdmin) {
        Usuario saved = usuarioService.saveAdmin(usuarioAdmin);

        if (saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(usuarioDtoConverter.convertUsuarioToGetUsuarioDto(saved));
    }









}
