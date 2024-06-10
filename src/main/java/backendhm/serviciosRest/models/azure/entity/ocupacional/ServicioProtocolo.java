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
@Table(name = "servicios_asignados_protocolos", uniqueConstraints ={@UniqueConstraint(columnNames = {"id_protocolo","id_servicio"})})
public class ServicioProtocolo  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_servicio_protocolo")
    private long idServicioProtocolo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_protocolo", nullable = false)
    private Protocolo protocolo_hm;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servicio", nullable = false)
    private Servicio servicios_hm;


    @Column(length = 12)
    private String precio;

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
