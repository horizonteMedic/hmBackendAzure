package backendhm.serviciosRest.models.azure.dtos.parametros;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleParametrosDTO {
    private Long id;

    private String descripcion;

    private Long idListaParametros;
}
