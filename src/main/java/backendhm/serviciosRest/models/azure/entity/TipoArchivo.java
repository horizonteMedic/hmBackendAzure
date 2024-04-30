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
@Table(name = "tipo_archivo")
public class TipoArchivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_tipo_archivo")
    private Long id;

    @Column(length = 150, name = "nombre_tipo_archivo")
    private String nombre;

    @Column(length = 20)
    private String extension;

    @Column(length = 20)
    private String color;

    @Column(length = 20)
    private String codigo;

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
