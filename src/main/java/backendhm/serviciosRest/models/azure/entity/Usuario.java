package backendhm.serviciosRest.models.azure.entity;

import backendhm.serviciosRest.auth.user.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "usuario", uniqueConstraints ={@UniqueConstraint(columnNames = {"username"})})
public class Usuario implements UserDetails {
   // private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_user")
    private long idUser;

    @Column(name = "username", nullable = false)
    private String username;

    private String password;

    @Column(length = 11)
    private String ruc;

    private Boolean estado;

    @Enumerated(EnumType.STRING)
    Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado", nullable = false)
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Empleado empleado;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "id_user",referencedColumnName = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "id_rol"),
            uniqueConstraints = {@UniqueConstraint(columnNames = { "id_user", "id_rol" }) })
    private List<Rol> rol;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     return List.of(new SimpleGrantedAuthority(role.name()));
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
}
