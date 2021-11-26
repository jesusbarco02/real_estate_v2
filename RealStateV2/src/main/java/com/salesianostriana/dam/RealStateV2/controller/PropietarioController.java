package com.salesianostriana.dam.RealStateV2.controller;

import com.salesianostriana.dam.RealStateV2.dto.propietarioDto.GetPropietarioDto;
import com.salesianostriana.dam.RealStateV2.dto.propietarioDto.GetPropietarioViviendaDto;
import com.salesianostriana.dam.RealStateV2.dto.propietarioDto.PropietarioDtoConverter;
import com.salesianostriana.dam.RealStateV2.model.Interesa;
import com.salesianostriana.dam.RealStateV2.security.jwt.JwtAuthorizationFilter;
import com.salesianostriana.dam.RealStateV2.security.jwt.JwtProvider;
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

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

   @Operation(summary = "Obtiene todos los propietarios creados")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200",
                   description = "Se han encontrado los propietarios",
                   content = { @Content(mediaType = "application/json",
                           schema = @Schema(implementation = Usuario.class))}),
           @ApiResponse(responseCode = "400",
                   description = "No se han encontrado los propietarios",
                   content = @Content),
           @ApiResponse(responseCode = "401",
                   description = "No se encuentra autorizado",
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

    @Operation(summary = "Obtiene el propietario que le indicamos por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el propietaroi especificado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado el propietario",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No se encuentra autorizado",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "No se encuentra los permisos para realizar la petición",
                    content = @Content),
    })
   @GetMapping("{id}")
   public ResponseEntity<List<GetPropietarioViviendaDto>> findOnePropietario(@PathVariable Long id,
                                                                             HttpServletRequest request,
                                                                             @AuthenticationPrincipal Usuario user) {
       Optional<Usuario> propietario = usuarioService.loadUserById(id);
       if (propietario.isEmpty()){
           return ResponseEntity.notFound().build();
       }else if (user.getRol().equals(Rol.ADMIN) || propietario.get().getRol().equals(user.getRol()) && propietario
               .get().getId().equals(user.getId())) {
           List<GetPropietarioViviendaDto> propietarioDto = propietario.stream()
                   .map(propietarioDtoConverter::propietarioToGetPropietarioViviendaDto)
                   .collect(Collectors.toList());
           return ResponseEntity.ok().body(propietarioDto);
       }else {
           return ResponseEntity.status(403).build();
       }
   }

   @Operation(summary = "Se elimina el propietario")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "204",
                   description = "Se ha borrado el propietario",
                   content = { @Content(mediaType = "application/json",
                           schema = @Schema(implementation = Usuario.class))}),
           @ApiResponse(responseCode = "404",
                   description = "No se ha borrado el propietario",
                   content = @Content),
           @ApiResponse(responseCode = "401",
                   description = "No se encuentra autorizado",
                   content = @Content),
           @ApiResponse(responseCode = "403",
                   description = "No se encuentra los permisos para realizar la petición",
                   content = @Content),
   })
   @DeleteMapping("{id}")
   public ResponseEntity<?> deletePropietario(@PathVariable Long id, HttpServletRequest request, @AuthenticationPrincipal Usuario user) {

       Optional<Usuario> propietario = usuarioService.loadUserById(id);

       if (propietario.isEmpty()){
           return ResponseEntity.notFound().build();
       }else if (user.getRol().equals(Rol.ADMIN) || (propietario.get().getRol().equals(user.getRol()) && propietario.get().getId().equals(user.getId()))) {
           usuarioService.deleteById(id);
           return ResponseEntity.noContent().build();
       }else {
           return ResponseEntity.status(403).build();
       }
   }


}
