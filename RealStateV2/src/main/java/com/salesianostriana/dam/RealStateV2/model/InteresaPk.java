package com.salesianostriana.dam.RealStateV2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InteresaPk implements Serializable {

    private Long vivienda_id;
    private Long usuario_id;
}
