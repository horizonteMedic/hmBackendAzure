package backendhm.serviciosRest.models.azure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "sede_hm_web", uniqueConstraints ={@UniqueConstraint(columnNames = {"codigo_sede"})})
public class SedeHmWeb implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_sede")
    private Long id;

    @Column(name = "nombre_sede", length = 50)
    private String nombreSede;

    @Column(name = "codigo_sede",length = 4,nullable = false)
    private String codigoSede;

    private Boolean estado;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;

    @Column(name = "user_registro", length = 20)
    private String userRegistro;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_actualizacion")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaActualizacion;

    @Column(name = "user_actualizacion", length = 20)
    private String userActualizacion;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_sede", joinColumns = @JoinColumn(name = "id_sede",referencedColumnName = "id_sede"),
            inverseJoinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id_user"),
            uniqueConstraints = {@UniqueConstraint(columnNames = { "id_sede", "id_user" }) })
    private List<Usuario> usuario;
}
