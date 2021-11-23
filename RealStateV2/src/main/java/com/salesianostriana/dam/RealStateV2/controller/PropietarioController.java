package com.salesianostriana.dam.RealStateV2.controller;

import com.salesianostriana.dam.RealStateV2.dto.propietarioDto.GetPropietarioDto;
import com.salesianostriana.dam.RealStateV2.dto.propietarioDto.GetPropietarioViviendaDto;
import com.salesianostriana.dam.RealStateV2.dto.propietarioDto.PropietarioDtoConverter;
import com.salesianostriana.dam.RealStateV2.security.jwt.JwtAuthorizationFilter;
import com.salesianostriana.dam.RealStateV2.security.jwt.JwtProvider;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@RequestMapping("/propietario/")
@RestController
public class PropietarioController {

   private final UsuarioService usuarioService;
   private final PropietarioDtoConverter propietarioDtoConverter;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final JwtProvider jwtProvider;

    @Operation(summary = "Obtiene todos los propietarios creados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado los propietarios",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se han encontrado los propietarios",
                    content = @Content),
    })
    @GetMapping("")
    public ResponseEntity<List<GetPropietarioDto>> findAll(){
        List<Usuario> data = usuarioService.loadUserByRole(Rol.PROPIETARIO);

        if (data.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            List<GetPropietarioDto> lista = data.stream().map(propietarioDtoConverter::propietarioToGetPropietarioDto).collect(Collectors.toList());
            return ResponseEntity.ok().body(lista);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<List<GetPropietarioViviendaDto>> findOnePropietario(@PathVariable Long id, HttpServletRequest request) {
        Optional<Usuario> propietario = usuarioService.loadUserById(id);

        String token = jwtAuthorizationFilter.getJwtFromRequest(request);
        Long idPropietario = jwtProvider.getUserIdFromJwt(token);

        if(propietario.get().getRol().equals(Rol.ADMIN) || propietario.get().getRol().equals(Rol.PROPIETARIO)){
            List<GetPropietarioViviendaDto> propietarioDto = propietario.stream()
                    .map(propietarioDtoConverter::propietarioToGetPropietarioViviendaDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(propietarioDto);
        }else {
            return ResponseEntity.notFound().build();
        }
        //return ResponseEntity.of(userEntityService.findById(id).map(userDtoConverter::propietarioToGetPropietarioConViviendas));
    }


}
