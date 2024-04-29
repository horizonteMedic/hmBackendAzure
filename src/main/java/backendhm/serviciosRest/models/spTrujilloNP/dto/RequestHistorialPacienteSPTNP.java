package backendhm.serviciosRest.models.spTrujilloNP.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestHistorialPacienteSPTNP {

    private String userName;

    private String fechaInicio;

    private String fechaFin;

    private long tipoUsuario;

    private String rucUser;

    private String sedeUser;


}
