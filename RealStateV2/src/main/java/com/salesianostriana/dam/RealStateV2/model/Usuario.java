package com.salesianostriana.dam.RealStateV2.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
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
    @OneToMany(mappedBy = "usuario")
    private List<Interesa> interesas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "inmobiliaria", foreignKey = @ForeignKey(name = "FK_VIVIENDA_INMOBILIARIA"))
    private Inmobiliaria inmobiliaria;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
