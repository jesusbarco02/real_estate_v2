package com.salesianostriana.dam.RealStateV2.security.jwt;


import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
@Log
@Service
public class JwtProvider {

    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.secret:proyectoinmobiliariasestamuyguapo}")
    private String jwtSecret;

    @Value("${jwt.duration:86400}") // 1 día
    private int jwtLifeInSeconds;

    private JwtParser parser;

    @PostConstruct
    public void init() {
        parser = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build();
    }

    public String generateToken(Authentication authentication) {

        Usuario usuario = (Usuario) authentication.getPrincipal();

        Date tokenExpirationDate = Date
                .from(LocalDateTime
                        .now()
                        .plusSeconds(jwtLifeInSeconds)
                        .atZone(ZoneId.systemDefault()).toInstant());


        return Jwts.builder()
                .setHeaderParam("typ", TOKEN_TYPE)
                .setSubject(Long.toString(usuario.getId()))
                .setIssuedAt(tokenExpirationDate)
                .claim("nombre", usuario.getNombre())
                .claim("apellidos",usuario.getApellidos())
                .claim("role", usuario.getRol().name())
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();


    }

    public Long getUserIdFromJwt(String token) {
        return Long.valueOf(parser.parseClaimsJws(token).getBody().getSubject());
    }

    public boolean validateToken(String token) {

        try {
            parser.parseClaimsJws(token);
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            log.info("Error con el token: " + ex.getMessage());
        }
        return false;
    }


}
