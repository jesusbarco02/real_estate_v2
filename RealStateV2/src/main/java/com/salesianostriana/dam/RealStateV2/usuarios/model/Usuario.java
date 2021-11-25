package com.salesianostriana.dam.RealStateV2.usuarios.model;

import com.salesianostriana.dam.RealStateV2.model.Inmobiliaria;
import com.salesianostriana.dam.RealStateV2.model.Interesa;
import com.salesianostriana.dam.RealStateV2.model.Vivienda;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String apellidos;

    private String direccion;


    @NaturalId
    @Column(unique = true, updatable = false)
    private String email;

    private String telefono;

    private String avatar;
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Builder.Default
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vivienda> listaViviendas = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Interesa> interesas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "inmobiliaria", foreignKey = @ForeignKey(name = "FK_VIVIENDA_INMOBILIARIA"))
    private Inmobiliaria inmobiliaria;

    public void addInmobiliaria(Inmobiliaria i) {
        this.inmobiliaria = i;
        i.getUsuarios().add(this);
    }

    public void removeInmobiliaria(Inmobiliaria i) {
        i.getUsuarios().remove(this);
        this.inmobiliaria = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Usuario(String nombre, String apellidos, String direccion, String email, String telefono, String avatar) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.avatar = avatar;
    }
}
