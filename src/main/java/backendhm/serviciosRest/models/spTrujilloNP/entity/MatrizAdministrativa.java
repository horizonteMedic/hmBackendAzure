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
@Table(name = "matriz_administrativa")
public class MatrizAdministrativa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_matriz_adm")
    private long id;

    @Column(name = "fecha_solicitud")
    private String fechaSolicitud;

    @Column(name = "apellidos_nombres")
    private String apellidosNombres;

    private Integer dni;

    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;

    private Integer edad;

    @Column(name = "edad_texto")
    private String edadTexto;

    @Column(name = "razon_contrata")
    private String razonContrata;

    private String cargo;

    @Column(name = "aptitud_emo")
    private String aptitudEmo;

    @Column(name = "fecha_examen")
    private String fechaExamen;

    private String observacion;

    private String celular;
}
