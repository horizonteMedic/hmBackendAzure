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
@Table(name = "response_matriz_salud")
public class ResponseMatrizSalud implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "n_orden")
    private long id;

    @Column(name = "fechasolicitud")
    private String fechaSolicitud;

    @Column(name = "apellidos_nombres")
    private String apellidosNombres;

    private Integer dni;

    @Column(name = "fechanacimiento")
    private String fechaNacimiento;

    private Integer edad;

    @Column(name = "razon_contrata")
    private String razonContrata;

    @Column(name = "razon_empresa")
    private String razonEmpresa;

    private String cargo;

    private String tipotrabajo;

    private String carnet;

    private String aptitud;

    private String fechadeevaluacion;

    private String peso;

    private String talla;

    private String imc;

    private String dxpeso;

    private String glucosa;

    private String colesterol;

    private String trigliceridos;

    private String dxoftalmo;

    private String dxaudio;

    private String restriccionesaptitud;


}
