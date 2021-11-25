package com.salesianostriana.dam.RealStateV2.model;

import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class    Inmobiliaria implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre, email;

    private String telefono;

    @OneToMany(mappedBy = "inmobiliaria")
    private List<Vivienda> viviendas =new ArrayList<>();

    @OneToMany(mappedBy = "inmobiliaria")
    private List<Usuario> usuarios = new ArrayList<>();

    public Inmobiliaria(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public Inmobiliaria(String nombre, String email, String telefono, List<Vivienda> viviendas) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.viviendas = viviendas;
    }
    @PreRemove
    public void preRemove() {
        viviendas.forEach( v -> v.setInmobiliaria(null));
    }
}
