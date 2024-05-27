package backendhm.serviciosRest.models.azure.entity.sistemaArchivos;

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
@Table(name = "archivos_servidores", uniqueConstraints ={@UniqueConstraint(columnNames = {"historia_clinica","id_tipo_archivo"})})
public class ArchivosServidor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_archivos")
    private Long id;

    @Column(name = "historia_clinica",length=20)
    private String historiaClinica;

    private Long orden;

    @Column(length = 200, name = "ruta_archivo")
    private String rutaArchivo;

    @Column(length = 100)
    private String nombre;

    @Column(length = 30, nullable = false)
    private String servidor;

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

    @Column( nullable = false)
    private long id_tipo_archivo;
}
