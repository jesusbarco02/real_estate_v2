package com.salesianostriana.dam.RealStateV2.usuarios.services;



import com.salesianostriana.dam.RealStateV2.model.Inmobiliaria;
import com.salesianostriana.dam.RealStateV2.services.base.BaseService;
import com.salesianostriana.dam.RealStateV2.usuarios.dto.CreateUsuarioDto;
import com.salesianostriana.dam.RealStateV2.usuarios.dto.CreateUsuarioGestorDto;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Rol;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import com.salesianostriana.dam.RealStateV2.usuarios.repos.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        if (nuevoGestor.getPassword().contentEquals(nuevoGestor.getPassword2())) {
            Usuario usuario = Usuario.builder()
                    .password(passwordEncoder.encode(nuevoGestor.getPassword()))
                    .avatar(nuevoGestor.getAvatar())
                    .nombre(nuevoGestor.getNombre())
                    .apellidos(nuevoGestor.getApellidos())
                    .email(nuevoGestor.getEmail())
                    .telefono(nuevoGestor.getTelefono())
                    .inmobiliaria(nuevoGestor.getInmobiliaria())
                    .rol(Rol.GESTOR)//PREGUNTAR QUE ROL DEBEMOS DE PONER
                    .build();
            return save(usuario);
        } else {
            return null;
        }
    }




}