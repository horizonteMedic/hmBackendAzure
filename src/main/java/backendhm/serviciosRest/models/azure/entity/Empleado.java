package backendhm.serviciosRest.models.azure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "empleado", uniqueConstraints ={@UniqueConstraint(columnNames = {"correo_elect"})})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_empleado")
    private long id;

    @Column(name = "num_documento")
    private long numDocumento;

    @Column(name = "tipo_doc", length = 30)
    private String tipoDoc;

    @Column(length = 50)
    private String nombres;

    @Column(length = 50)
    private String apellidos;

    private String sexo;

    @Column(length = 50)
    private String cargo;

    @Column(length = 6)
    private String ubigeo;

    @Column(length = 20)
    private String cip;

    @Column(name = "correo_elect")
    private String correoElect;

    private String celular;

    @Column(name = "tel_fijo")
    private String telFijo;

    private String direccion;

    private Boolean estado;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

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

    @JsonBackReference
    @OneToMany(mappedBy="empleado", cascade= CascadeType.ALL,orphanRemoval = true)
    private Set<Usuario> usuario=new HashSet<>();


}
