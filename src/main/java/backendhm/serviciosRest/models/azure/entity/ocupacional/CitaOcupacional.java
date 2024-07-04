package backendhm.serviciosRest.models.azure.entity.ocupacional;

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
@Table(name = "cita_ocupacional", uniqueConstraints ={@UniqueConstraint(columnNames = {"dni","fecha_reserva"})})
public class CitaOcupacional implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_cita_ocupacional")
    private long idCitaOcupacional;

    @Column(nullable = false)
    Long dni;

    Long celular;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_reserva")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaReserva;

    @Column(length = 20)
    private String nomenSede;

    @Column(name = "ruc_empresa")
    private Long rucEmpresa;

    @Column(name = "ruc_contrata")
    private Long rucContrata;

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