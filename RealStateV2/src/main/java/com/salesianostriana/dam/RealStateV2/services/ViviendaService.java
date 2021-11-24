package com.salesianostriana.dam.RealStateV2.services;

import com.salesianostriana.dam.RealStateV2.dto.viviendaDto.GetViviendaDto;
import com.salesianostriana.dam.RealStateV2.model.Vivienda;
import com.salesianostriana.dam.RealStateV2.repos.ViviendaRepository;
import com.salesianostriana.dam.RealStateV2.services.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViviendaService extends BaseService<Vivienda, Long, ViviendaRepository> {

    public List<GetViviendaDto> listarViviendasDto() {
        return repositorio.todosLasViviendasDto();
    }

}