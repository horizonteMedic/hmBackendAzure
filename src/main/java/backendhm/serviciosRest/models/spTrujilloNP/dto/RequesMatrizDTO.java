package backendhm.serviciosRest.models.spTrujilloNP.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequesMatrizDTO {

    private String fechaInicio;
    private String fechaFinal;
    private String rucEmpresa;
    private String rucContrata;
    private String sede;
}
