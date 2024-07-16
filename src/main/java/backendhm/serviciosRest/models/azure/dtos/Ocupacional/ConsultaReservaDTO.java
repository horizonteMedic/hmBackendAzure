package backendhm.serviciosRest.models.azure.dtos.Ocupacional;

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
public class ConsultaReservaDTO {

    private long id_resp;

    private String rucEmpresa;

    private String rucContrata;

    private String cargo;

    private String area;

    private String tipoExamen;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaReserva;
}
