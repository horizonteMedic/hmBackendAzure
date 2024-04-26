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
@Table(name = "detalle_historia_clinica")
public class DetalleHistoriaClinica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "historia_clinica")
    private Long historiaClinica;

    @Column(length = 100)
    private String empresa;

    @Column(length = 100)
    private String contrata;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_apertura")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaApertura;

    @Column(length = 100)
    private String examen;

    @Column(length = 100)
    private String estado;


    @Column(length = 100)
    private String cargo;

    @Column(length = 100)
    private String area;

    @Column(length = 100)
    private String grupoSanguineo;
}
