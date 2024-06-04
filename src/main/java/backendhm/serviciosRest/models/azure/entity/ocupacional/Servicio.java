package backendhm.serviciosRest.models.azure.entity.ocupacional;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "servicios_hm")
public class Servicio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_servicio")
    private long idServicio;

    @Column(name = "nombre_servicio")
    private String nombreServicio;

    @Column(name = "tabla_servicio")
    private String tablaServicio;

    private String money;

    private Boolean estado;

}
