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
@Table(name = "codigo_generado")
public class CodigoGenerado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_codigo")
    private Long idCodigo;

    @Column(length = 6)
    private String codigo;

    @Column(length = 20)
    private String usuario;

    private Boolean estado;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;

}
