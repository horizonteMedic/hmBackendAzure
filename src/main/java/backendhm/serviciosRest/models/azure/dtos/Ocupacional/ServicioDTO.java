package backendhm.serviciosRest.models.azure.dtos.Ocupacional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicioDTO {

    private long idServicio;

    private String nombreServicio;

    private String money;

    private Boolean estado;

}
