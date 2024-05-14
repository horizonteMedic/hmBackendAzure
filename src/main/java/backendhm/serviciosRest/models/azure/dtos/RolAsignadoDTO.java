package backendhm.serviciosRest.models.azure.dtos;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class RolAsignadoDTO {
    private long idRolAsignado;

    private long idRol;

    private long idRolAsignar;

    private Boolean estado;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;

    private String userRegistro;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaActualizacion;

    private String userActualizacion;
}
