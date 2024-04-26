package backendhm.serviciosRest.models.azure.dtos.combobox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinciaDTO {

    private String id;

    private String nombre;

    private String idDepartamento;
}
