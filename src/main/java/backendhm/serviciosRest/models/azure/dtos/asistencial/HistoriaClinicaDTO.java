package backendhm.serviciosRest.models.azure.dtos.asistencial;

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
public class HistoriaClinicaDTO {

    private String id;

    private String nroDoc;

    private String rucEmpresa;

    private String rucContrata;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;

    private String userRegistro;

    private String sede;

}
