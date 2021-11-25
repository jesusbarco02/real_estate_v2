package com.salesianostriana.dam.RealStateV2.dto.interesadoDto;

import com.salesianostriana.dam.RealStateV2.model.InteresaPk;
import com.salesianostriana.dam.RealStateV2.usuarios.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GetInteresadoDto {

    private InteresaPk id;
    private String mensaje;
    private String nombreUsuario;
    private String apellidosUsuario;


}
