package com.salesianostriana.dam.RealStateV2.dto.interesadoDto;

import com.salesianostriana.dam.RealStateV2.model.Interesa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetInteresaDto {

    private String mensaje;
    private LocalDate createDate;
    private String tituloVivienda;
    private String provinciaVivienda;
    private int numBaniosVivienda;
    private int numHabitacionesVivienda;
    private double precioVivienda;
    private String descripcionVivienda;
    private String avatarVivienda;
    private List<Interesa> interesa;
}
