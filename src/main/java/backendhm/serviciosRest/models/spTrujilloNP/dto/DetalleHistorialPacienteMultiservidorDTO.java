package backendhm.serviciosRest.models.spTrujilloNP.dto;

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
public class DetalleHistorialPacienteMultiservidorDTO {

    private String historiaClinica;

    private Integer orden;

    private String empresa;

    private String contrata;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaExamen;

    private String examen;

    private String estado;

    private String cargo;

    private String area;

    private String grupoSanguineo;


}
