package com.salesianostriana.dam.RealStateV2.usuarios.services;



import com.salesianostriana.dam.RealStateV2.security.dto.LoginDto;
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


    public Usuario savePropietario(CreateUsuarioDto nuevoUsuario) {
        if (nuevoUsuario.getPassword().contentEquals(nuevoUsuario.getPassword2())) {
            Usuario userEntity = Usuario.builder()
                    .password(passwordEncoder.encode(nuevoUsuario.getPassword()))
                    .avatar(nuevoUsuario.getAvatar())
                    .nombre(nuevoUsuario.getNombre())
                    .apellidos(nuevoUsuario.getApellidos())
                    .email(nuevoUsuario.getEmail())
                    .telefono((nuevoUsuario.getTelefono()))
                    .rol(Rol.PROPIETARIO)//PREGUNTAR QUE ROL DEBEMOS DE PONER
                    .build();
            return save(userEntity);
        } else {
            return null;
        }
    }







}