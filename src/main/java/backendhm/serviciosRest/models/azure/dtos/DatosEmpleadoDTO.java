package backendhm.serviciosRest.models.azure.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatosEmpleadoDTO {

    private long idEmpleado;
    private String nombres;
    private String apellidos;
}
