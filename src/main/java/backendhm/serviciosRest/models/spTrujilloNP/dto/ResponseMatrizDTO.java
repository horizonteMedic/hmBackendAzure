package backendhm.serviciosRest.models.spTrujilloNP.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMatrizDTO {
    String id;
    String fechaSolicitud;
    String apellidosNombres;
    Integer dni;
    String fechaNacimiento;
    String edad;
    String razonContrata;
    String cargo;
    String aptitudEmo;
    String fechaExamen;
    String observacion;

}
