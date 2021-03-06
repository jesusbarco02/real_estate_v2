package com.salesianostriana.dam.RealStateV2.usuarios.services;



import com.salesianostriana.dam.RealStateV2.model.Inmobiliaria;
import com.salesianostriana.dam.RealStateV2.model.Vivienda;
import com.salesianostriana.dam.RealStateV2.services.InmobiliariaService;
import com.salesianostriana.dam.RealStateV2.services.base.BaseService;
import com.salesianostriana.dam.RealStateV2.usuarios.dto.CreateUsuarioDto;
import com.salesianostriana.dam.RealStateV2.usuarios.dto.CreateUsuarioGestorDto;
import com.salesianostriana.dam.RealStateV2.usuarios.dto.GetUsuarioDto;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Rol;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import com.salesianostriana.dam.RealStateV2.usuarios.repos.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service("usuarioDetailsService")
@RequiredArgsConstructor
public class UsuarioService extends BaseService<Usuario, Long, UsuarioRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final InmobiliariaService inmobiliariaService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repositorio.findFirstByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email + " no encontrado"));
    }


    public Usuario savePropietario(CreateUsuarioDto nuevoUsuario) {
        if (nuevoUsuario.getPassword().contentEquals(nuevoUsuario.getPassword2())) {
            Usuario usuario = Usuario.builder()
                    .password(passwordEncoder.encode(nuevoUsuario.getPassword()))
                    .avatar(nuevoUsuario.getAvatar())
                    .nombre(nuevoUsuario.getNombre())
                    .apellidos(nuevoUsuario.getApellidos())
                    .email(nuevoUsuario.getEmail())
                    .telefono(nuevoUsuario.getTelefono())
                    .direccion(nuevoUsuario.getDireccion())
                    .rol(Rol.PROPIETARIO)//PREGUNTAR QUE ROL DEBEMOS DE PONER
                    .build();
            return save(usuario);
        } else {
            return null;
        }
    }


    public Usuario saveGestor(CreateUsuarioGestorDto nuevoGestor){
        Optional <Inmobiliaria> inmobiliaria= inmobiliariaService.findById(nuevoGestor.getInmobiliaria());
        Inmobiliaria inmo = inmobiliaria.get();
        if (nuevoGestor.getPassword().contentEquals(nuevoGestor.getPassword2())) {
            Usuario usuario = Usuario.builder()
                    .password(passwordEncoder.encode(nuevoGestor.getPassword()))
                    .avatar(nuevoGestor.getAvatar())
                    .nombre(nuevoGestor.getNombre())
                    .apellidos(nuevoGestor.getApellidos())
                    .email(nuevoGestor.getEmail())
                    .telefono(nuevoGestor.getTelefono())
                    .inmobiliaria(inmo)
                    .direccion(nuevoGestor.getDireccion())
                    .rol(Rol.GESTOR)//PREGUNTAR QUE ROL DEBEMOS DE PONER
                    .build();

            usuario.addInmobiliaria(inmobiliaria.get());
            return save(usuario);
        } else {
            return null;
        }
    }

    public Usuario saveAdmin(CreateUsuarioDto nuevoAdmin){
        if (nuevoAdmin.getPassword().contentEquals(nuevoAdmin.getPassword2())) {
            Usuario usuario = Usuario.builder()
                    .password(passwordEncoder.encode(nuevoAdmin.getPassword()))
                    .avatar(nuevoAdmin.getAvatar())
                    .nombre(nuevoAdmin.getNombre())
                    .apellidos(nuevoAdmin.getApellidos())
                    .email(nuevoAdmin.getEmail())
                    .telefono(nuevoAdmin.getTelefono())
                    .direccion(nuevoAdmin.getDireccion())
                    .rol(Rol.ADMIN)//PREGUNTAR QUE ROL DEBEMOS DE PONER
                    .build();
            return save(usuario);
        } else {
            return null;
        }
    }

    public List<Usuario> loadUserByRole(Rol rol) throws UsernameNotFoundException{
        return this.repositorio.findByRol(rol);
    }

    public Optional<Usuario> loadUserById(Long id) throws UsernameNotFoundException{
        return this.repositorio.findById(id);
    }

    @PostConstruct
    public List<Usuario> findAll(){
        repositorio.findAll().stream().forEach(u -> {
            u.setPassword(passwordEncoder.encode(u.getPassword()));
            repositorio.save(u);
        });
        return repositorio.findAll();
    }

}