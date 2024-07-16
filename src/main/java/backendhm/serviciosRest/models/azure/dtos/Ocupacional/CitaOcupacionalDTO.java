package backendhm.serviciosRest.models.azure.dtos.Ocupacional;

import jakarta.persistence.*;
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
public class CitaOcupacionalDTO {

    private long idCitaOcupacional;

    Long dni;

    Long celular;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaReserva;

    private String nomenSede;

    private Long rucEmpresa;

    private String razonEmpresa;

    private Long rucContrata;

    private String razonContrata;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;

    private String userRegistro;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaActualizacion;

    private String userActualizacion;

    private String cargo;

    private String area;

    private String tipoExamen;

}
