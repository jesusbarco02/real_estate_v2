package com.salesianostriana.dam.RealStateV2.usuarios.controller;

import com.salesianostriana.dam.RealStateV2.usuarios.dto.CreateUsuarioDto;
import com.salesianostriana.dam.RealStateV2.usuarios.dto.GetUsuarioDto;
import com.salesianostriana.dam.RealStateV2.usuarios.dto.UsuarioDtoConverter;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import com.salesianostriana.dam.RealStateV2.usuarios.services.UsuarioService;
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

    @PostMapping("/auth/register")
    public ResponseEntity<GetUsuarioDto> nuevoUsuario(@RequestBody CreateUsuarioDto nuevoUsuario) {
        Usuario saved = usuarioService.save(nuevoUsuario);

        if (saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(usuarioDtoConverter.convertUsuarioEntityToGetUsuarioDto(saved));

    }
}
