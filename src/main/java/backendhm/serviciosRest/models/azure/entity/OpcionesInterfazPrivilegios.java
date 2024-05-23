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
@Table(name = "opciones_interfaz_privilegios")
public class OpcionesInterfazPrivilegios implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_opcion_interfaz")
    private Long id;

    @Column(length = 50)
    private String nombre;

    @Column(length = 100)
    private String rutaVista;

    @Column(length = 200)
    private String descripcion;

    private Boolean estado;

    private Long nivel;

    @Column(name = "id_padre")
    private Long idPadre;

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
    @JoinTable(name = "privilegio_rol", joinColumns = @JoinColumn(name = "id_opcion_interfaz",referencedColumnName = "id_opcion_interfaz"),
            inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "id_rol"),
            uniqueConstraints = {@UniqueConstraint(columnNames = { "id_opcion_interfaz", "id_rol" }) })
    private List<Rol> rol;
}
