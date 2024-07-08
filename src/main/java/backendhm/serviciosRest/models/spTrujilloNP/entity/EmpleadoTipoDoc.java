package backendhm.serviciosRest.models.spTrujilloNP.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "empleado_tipo_doc")
public class EmpleadoTipoDoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_empleado_tipo_doc;

    private long dni;

    @Column(name = "tipo_archivo")
    private  String tipoArchivo;

    @Column(name = "nombre_archivo")
    private String nombreArchivo;

    private String ruta;

    @Column(length = 20)
    private String extension;

}
