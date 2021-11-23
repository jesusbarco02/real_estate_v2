package com.salesianostriana.dam.RealStateV2.security;


import com.salesianostriana.dam.RealStateV2.security.dto.JwtUsuarioResponse;
import com.salesianostriana.dam.RealStateV2.security.dto.LoginDto;
import com.salesianostriana.dam.RealStateV2.security.jwt.JwtProvider;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDto.getEmail(),
                                loginDto.getPassword()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);


        String jwt = jwtProvider.generateToken(authentication);


        Usuario usuario = (Usuario) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertUsuarioToJwtUsuariorResponse(usuario, jwt));

    }

    @GetMapping("/me")
    public ResponseEntity<?> identificarme(@AuthenticationPrincipal Usuario usuario){
        return ResponseEntity.ok(convertUsuarioToJwtUsuariorResponse(usuario, null));
    }


    private JwtUsuarioResponse convertUsuarioToJwtUsuariorResponse(Usuario usuario, String jwt) {
        return JwtUsuarioResponse.builder()
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .email(usuario.getEmail())
                .avatar(usuario.getAvatar())
                .role(usuario.getRol().name())
                .token(jwt)
                .build();
    }


}
