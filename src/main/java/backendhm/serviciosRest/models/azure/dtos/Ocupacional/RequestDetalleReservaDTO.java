package backendhm.serviciosRest.models.azure.dtos.Ocupacional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDetalleReservaDTO {
    private String nombreSede;

    private String fechaReserva;

    private String nombreUser;
}
