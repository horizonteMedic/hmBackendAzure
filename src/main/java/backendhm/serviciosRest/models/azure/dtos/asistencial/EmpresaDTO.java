package backendhm.serviciosRest.models.azure.dtos.asistencial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO {
    private String rucEmpresa;

    private String razonEmpresa;

    private String direccionEmpresa;

    private String telefonoEmpresa;

    private String responsableEmpresa;

    private String emailEmpresa;

    private String apiToken;
}
