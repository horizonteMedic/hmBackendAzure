package backendhm.serviciosRest.models.azure.dtos.asistencial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContrataDTO {

    private String rucContrata;

    private String razonContrata;

    private String direccionContrata;

    private String telefonoContrata;

    private String responsableContrata;

    private String emailContrata;
}
