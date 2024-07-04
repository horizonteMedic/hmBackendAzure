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
public class BackendDetalleReservaDTO {
    private long idResp;

    private String sede;

    private long dni;

    private String empresa;

    private String contrata;

    private String programador;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
}
