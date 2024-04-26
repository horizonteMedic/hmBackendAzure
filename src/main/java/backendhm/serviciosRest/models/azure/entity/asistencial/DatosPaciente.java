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
@Table(name = "datos_paciente_asist")
public class DatosPaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "num_doc")
    private String id;

    @Column(length = 20, name = "tipo_doc")
    private String tipoDoc;

    @Column(length = 50)
    private String nombres;

    @Column(length = 50)
    private String apellidos;

    @Column(length = 10)
    private String sexo;

    @Column(length = 15, name = "estado_civil")
    private String estadoCivil;

    @Column(length = 30, name = "nivel_estudio")
    private String nivelEstudio;

    @Column(length = 100)
    private String ocupacion;

    @Column(length = 50)
    private String email;

    @Column(length = 12)
    private String celular;

    @Column(length = 6)
    private String ubigeo;

    @Column(length = 150)
    private String dirrecion;

    @Column(length = 150)
    private String caserio;

    @Column(length = 150,name = "lugar_nac")
    private String lugarNac;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nac")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNac;

    private Boolean estado;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;

    @Column(name = "user_registro", length = 20)
    private String userRegistro;
}
