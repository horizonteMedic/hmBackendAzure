package backendhm.serviciosRest.models.spTrujilloNP.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestHistorialPacienteMultiservidor {

    private String userName;

    private String fechaInicio;

    private String fechaFin;

    private long tipoUsuario;

    private String rucUser;

    private String sedeUser;


}
