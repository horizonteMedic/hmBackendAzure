package backendhm.serviciosRest.models.azure.dtos.Ocupacional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMatrizArchivosDTO {

    private long dni;
    private String razonEmpresa;
    private String razonCOntrata;
    private String historiaClinica;
    private Long norden;
    private String nombreArchivo;
    private String fechaRegistro;
    private String userRegistro;

}
