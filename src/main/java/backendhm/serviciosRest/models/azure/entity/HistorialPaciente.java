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
@Table(name = "historial_paciente")
public class HistorialPaciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_historial_paciente")
    private Long id;

    private Long dni;

    @Column(length = 50)
    private String apellidos;

    @Column(length = 50)
    private String nombres;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_examen")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaExamen;

}
