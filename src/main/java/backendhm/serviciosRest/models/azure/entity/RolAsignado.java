package backendhm.serviciosRest.models.azure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "rol_asignado", uniqueConstraints ={@UniqueConstraint(columnNames = {"id_rol_asignar","id_rol"})})
public class RolAsignado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_rol_asignado")
    private long idRolAsignado;

    @Column(name = "id_rol",nullable = false)
    private long idRol;
    @Column(name = "id_rol_asignar",nullable = false)
    private long idRolAsignar;

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
}
