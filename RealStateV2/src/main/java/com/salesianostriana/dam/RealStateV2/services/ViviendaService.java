package com.salesianostriana.dam.RealStateV2.services;

import com.salesianostriana.dam.RealStateV2.dto.viviendaDto.GetViviendaDto;
import com.salesianostriana.dam.RealStateV2.dto.viviendaDto.GetViviendaPropietarioDto;
import com.salesianostriana.dam.RealStateV2.model.Vivienda;
import com.salesianostriana.dam.RealStateV2.repos.ViviendaRepository;
import com.salesianostriana.dam.RealStateV2.services.base.BaseService;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViviendaService extends BaseService<Vivienda, Long, ViviendaRepository> {

    public List<GetViviendaDto> listarViviendasDto() {
        return repositorio.todosLasViviendasDto();
    }
    public List<GetViviendaDto> listarViviendasPropietario(Long id) {
        return repositorio.todosLasViviendasPropietario(id);
    }


    public Optional<Vivienda> loadUserById(Long id) throws UsernameNotFoundException {
        return this.repositorio.findById(id);
    }

}