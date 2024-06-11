package backendhm.serviciosRest.models.spTrujilloNP.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "backend_entity_datos_paciente")
public class BackendEntityDatosPaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "cod_pa")
    private long codPa;

    @Column(name = "nombres_pa")
    private String nombresPa;

    @Column(name = "fecha_nacimiento_pa")
    private String fechaNaciminetoPa;

    @Column(name = "sexo_pa")
    private String sexoPa;

    @Column(name = "email_pa")
    private String emailPa;

    @Column(name = "lugar_nac_pa")
    private String lugarNacPa;

    @Column(name = "nivel_est_pa")
    private String nivelEstPa;

    @Column(name = "ocupacion_pa")
    private String ocupacionPa;

    @Column(name = "estado_civil_pa")
    private String estadoCivilPa;

    @Column(name = "direccion_pa")
    private String direccionPa;

    @Column(name = "departamento_pa")
    private String departamentoPa;

    @Column(name = "provincia_pa")
    private String provinciaPa;

    @Column(name = "distrito_pa")
    private String distritoPa;

    @Column(name = "caserio_pa")
    private String caserioPA;

    @Column(name = "foto_pa")
    private String fotoPa;

    @Column(name = "cod_aleatorio_pa")
    private long codAleatorioPa;

    @Column(name = "tel_casa_pa")
    private String telCasaPa;

    @Column(name = "tel_trabajo_pa")
    private String telTrabajoPa;

    @Column(name = "cel_pa")
    private String celPa;

    @Column(name = "fecha_registro_pa")
    private String fechaRegistroPa;

    @Column(name = "apellidos_pa")
    private String apellidosPa;

    @Column(name = "hora_registro_pa")
    private String horaRegistroPa;

    @Column(name = "tipo_doc")
    private long tipoDoc;

}
