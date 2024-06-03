package backendhm.serviciosRest.models.spTrujilloSD.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaBackendDTOTSD {
    private Long id;

    private String mensaje;
}
