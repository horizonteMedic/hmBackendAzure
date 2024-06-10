package backendhm.serviciosRest.models.azure.dtos.Ocupacional;

import backendhm.serviciosRest.models.azure.entity.ocupacional.Protocolo;
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
public class ContratoProtocoloDTO {

    private long idContratoProtocolo;

    Long rucContrata;

    private float precio;


    private long id_protocolo;


    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;

    private String userRegistro;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaActualizacion;

    private String userActualizacion;
}
