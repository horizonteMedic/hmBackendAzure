package backendhm.serviciosRest.models.azure.entity.asistencial;

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
@Table(name = "historia_clinica")
public class HistoriaClinica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "n_orden")
    private String id;

    @Column(name="nro_doc", length = 20)
    private String nroDoc;

    @Column(name = "ruc_empresa", length = 11)
    private String rucEmpresa;

    @Column(name= "ruc_contrata", length = 11)
    private String rucContrata;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;

    @Column(name = "user_registro", length = 20)
    private String userRegistro;

    private String sede;


}
