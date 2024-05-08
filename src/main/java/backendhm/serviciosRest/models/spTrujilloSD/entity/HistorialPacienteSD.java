package backendhm.serviciosRest.models.spTrujilloSD.entity;

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
public class HistorialPacienteSD implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;

    private String codigo;

    private Integer dni;

    @Column(length = 50)
    private String apellido;

    @Column(length = 50)
    private String nombre;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
}
