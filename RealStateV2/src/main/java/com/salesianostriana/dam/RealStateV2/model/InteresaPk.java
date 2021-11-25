package com.salesianostriana.dam.RealStateV2.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InteresaPk implements Serializable {

    private Long vivienda_id;
    private Long usuario_id;
}
