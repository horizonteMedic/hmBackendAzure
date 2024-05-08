package backendhm.serviciosRest.models.spTrujilloSD.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "detalle_historial_paciente")
public class DetalleHistorialPacienteSD implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;

    @Column(name = "historia_clinica", length = 20)
    private String historiaClinica;

    private Integer orden;

    private String empresa;

    private String contrata;

    @Column(name = "fecha_examen")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaExamen;

    private String examen;

    private String estado;

    private String cargo;

    private String area;

    @Column(name = "grupo_sanguineo")
    private String grupoSanguineo;
}
