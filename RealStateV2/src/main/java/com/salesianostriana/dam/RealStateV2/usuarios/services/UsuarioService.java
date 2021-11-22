package com.salesianostriana.dam.RealStateV2.usuarios.services;



import com.salesianostriana.dam.RealStateV2.services.base.BaseService;
import com.salesianostriana.dam.RealStateV2.usuarios.dto.CreateUsuarioDto;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Rol;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import com.salesianostriana.dam.RealStateV2.usuarios.repos.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service("usuarioDetailsService")
@RequiredArgsConstructor
public class UsuarioService extends BaseService<Usuario, Long, UsuarioRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repositorio.findFirstByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email + " no encontrado"));
    }


    public Usuario save(CreateUsuarioDto newUser) {
        if (newUser.getPassword().contentEquals(newUser.getPassword2())) {
            Usuario userEntity = Usuario.builder()
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .avatar(newUser.getAvatar())
                    .nombre(newUser.getNombre())
                    .apellidos(newUser.getApellidos())
                    .email(newUser.getEmail())
                    .rol(Rol.ADMIN)//PREGUNTAR QUE ROL DEBEMOS DE PONER
                    .build();
            return save(userEntity);
        } else {
            return null;
        }
    }


}