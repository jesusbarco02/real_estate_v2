package com.salesianostriana.dam.RealStateV2.model;

import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Interesa {

    @Builder.Default
    @EmbeddedId
    private InteresaPk id = new InteresaPk();

    @ManyToOne
    @MapsId("vivienda_id")
    @JoinColumn(name="vivienda_id")
    private Vivienda vivienda;

    @ManyToOne
    @MapsId("usuario_id")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @CreatedDate
    private LocalDate createdDate;

    private String mensaje;



    public void addToVivienda(Vivienda v){
        vivienda = v;
        v.getInteresas().add(this);
    }

    public void removeFromVivienda(Vivienda v){
        v.getInteresas().remove(this);
        vivienda=null;
    }

    public void addToUsuario(Usuario u){
        usuario = u;
        if (u.getInteresas() == null){
            u.setInteresas(new ArrayList<>());
            u.getInteresas().add(this);
        }
        u.getInteresas().add(this);
    }

    public void removeFromInteresado(Usuario u){
        u.getInteresas().remove(this);
        usuario=null;
    }
}
