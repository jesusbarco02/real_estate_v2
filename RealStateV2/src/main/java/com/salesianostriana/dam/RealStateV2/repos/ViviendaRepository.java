package com.salesianostriana.dam.RealStateV2.repos;


import com.salesianostriana.dam.RealStateV2.dto.viviendaDto.GetViviendaDto;
import com.salesianostriana.dam.RealStateV2.dto.viviendaDto.GetViviendaPropietarioDto;
import com.salesianostriana.dam.RealStateV2.model.Vivienda;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ViviendaRepository extends JpaRepository<Vivienda, Long> {

    @Query("""
            select new com.salesianostriana.dam.RealStateV2.dto.viviendaDto.GetViviendaDto(
                v.id, v.titulo, v.provincia, v.numBanios, v.numHabitaciones, v.metrosCuadrados, v.precio, v.descripcion, v.avatar
            )
            from Vivienda v 
            """)
    List<GetViviendaDto> todosLasViviendasDto();

    Optional<Vivienda> findById(Long id);


    @Query("""
            select new com.salesianostriana.dam.RealStateV2.dto.viviendaDto.GetViviendaDto(
                v.id, v.titulo, v.provincia, v.numBanios, v.numHabitaciones, v.metrosCuadrados, v.precio, v.descripcion, v.avatar
            )
            from Vivienda v where v.usuario.id = :id
            """)
    List<GetViviendaDto> todosLasViviendasPropietario(Long id);



}