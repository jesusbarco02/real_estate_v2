package com.salesianostriana.dam.RealStateV2.services;


import com.salesianostriana.dam.RealStateV2.services.base.BaseService;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import com.salesianostriana.dam.RealStateV2.usuarios.repos.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class PropietarioService extends BaseService<Usuario, Long, UsuarioRepository> {
}
