package backendhm.serviciosRest.models.azure.dtos.campaña;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CIE10CAMPAÑADTO {
    private String codigo;

    private  String descripcion;
}
