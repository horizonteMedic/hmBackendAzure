package backendhm.serviciosRest.models.spTrujilloNP.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialPacienteMultiservidorDto {

    private String codigo_sucursal;

    private long dni;

    private String apellidos;

    private String nombres;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha_examen;

}
