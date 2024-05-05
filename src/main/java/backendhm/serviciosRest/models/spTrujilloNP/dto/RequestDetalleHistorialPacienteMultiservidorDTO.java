package backendhm.serviciosRest.models.spTrujilloNP.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDetalleHistorialPacienteMultiservidorDTO {
    private String userName;

    private String fechaInicio;

    private String fechaFin;

    private String tipo;

    private String rucEmpresa;

    private String rucContrata;

    private String sedeUser;

    private String dniUser;
}
