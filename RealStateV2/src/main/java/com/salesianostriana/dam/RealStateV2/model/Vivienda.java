package com.salesianostriana.dam.RealStateV2.model;

import com.salesianostriana.dam.RealStateV2.usuarios.model.Usuario;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vivienda implements Serializable {

    @Id
    @GeneratedValue
    private Long id;


    private String titulo;
    @Lob
    private String descripcion;
    @Lob
    private String avatar;
    private String latlng;
    private String direccion;
    private String codigoPostal;
    private String poblacion;
    private String provincia;

    @Enumerated(EnumType.STRING)
    private TipoVivienda tipoVivienda;
    private double precio;
    private int numHabitaciones;
    private int numBanios;
    private double metrosCuadrados;
    private boolean tienePiscina;
    private boolean tieneAscensor;
    private boolean tieneGaraje;

    @ManyToOne
    @JoinColumn(name = "inmobiliaria", foreignKey = @ForeignKey(name = "FK_VIVIENDAINMOBILIARIA"))
    private Inmobiliaria inmobiliaria;

    @ManyToOne
    @JoinColumn(name = "usuario", foreignKey = @ForeignKey(name = "FK_VIVIENDA_USUARIO"))
    private Usuario usuario;

    @Builder.Default
    @OneToMany(mappedBy = "vivienda", cascade = {CascadeType.REMOVE})
    private List<Interesa> interesas = new ArrayList<>();



    public void addInmobiliaria(Inmobiliaria i){
        this.inmobiliaria = i;
        i.getViviendas().add(this);
    }

    public void removeInmobiliaria(Inmobiliaria i){
        i.getViviendas().remove(this);
        this.inmobiliaria=null;
    }

    public void addUsuario(Usuario u){
        this.usuario = u;
        u.getListaViviendas().add(this);
    }

    public void removeUsuario(Usuario u){
        u.getListaViviendas().remove(this);
        this.usuario=null;
    }

    public Vivienda(String titulo, String descripcion, String provincia, double precio, int numHabitaciones, int numBaños, double metrosCuadrados, String avatar) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.provincia = provincia;
        this.precio = precio;
        this.numHabitaciones = numHabitaciones;
        this.numBanios = numBaños;
        this.metrosCuadrados = metrosCuadrados;
        this.avatar = avatar;
    }

    public Vivienda(String titulo, String descripcion, String avatar, String latlng, String direccion, String codigoPostal, String poblacion, String provincia, TipoVivienda tipoVivienda, double precio, int numHabitaciones, int numBanios, double metrosCuadrados, boolean tienePiscina, boolean tieneAscensor, boolean tieneGaraje) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.avatar = avatar;
        this.latlng = latlng;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.tipoVivienda = tipoVivienda;
        this.precio = precio;
        this.numHabitaciones = numHabitaciones;
        this.numBanios = numBanios;
        this.metrosCuadrados = metrosCuadrados;
        this.tienePiscina = tienePiscina;
        this.tieneAscensor = tieneAscensor;
        this.tieneGaraje = tieneGaraje;
    }
}


