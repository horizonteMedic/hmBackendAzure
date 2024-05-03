package backendhm.serviciosRest.models.azure.dtos.sistemaArchivos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpContDTO {
    private String ruc;
    private String razonSocial;
}
