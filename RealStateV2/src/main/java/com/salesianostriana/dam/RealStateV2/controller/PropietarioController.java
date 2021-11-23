package com.salesianostriana.dam.RealStateV2.controller;

import com.salesianostriana.dam.RealStateV2.dto.propietarioDto.GetPropietarioDto;
import com.salesianostriana.dam.RealStateV2.dto.propietarioDto.PropietarioDtoConverter;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Rol;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import com.salesianostriana.dam.RealStateV2.usuarios.services.UsuarioService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@RequestMapping("/propietario/")
@RestController
public class PropietarioController {

   private final UsuarioService usuarioService;
   private final PropietarioDtoConverter propietarioDtoConverter;


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


}
