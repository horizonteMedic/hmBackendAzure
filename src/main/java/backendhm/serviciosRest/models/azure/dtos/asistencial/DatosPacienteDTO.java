package backendhm.serviciosRest.models.azure.dtos.asistencial;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatosPacienteDTO {

    private String numDoc;

    private String tipoDoc;

    private String nombres;

    private String apellidos;

    private String sexo;

    private String estadoCivil;

    private String nivelEstudio;

    private String ocupacion;

    private String email;

    private String celular;

    private String ubigeo;

    private String dirrecion;

    private String caserio;

    private String lugarNac;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNac;

    private Boolean estado;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;

    private String userRegistro;
}
