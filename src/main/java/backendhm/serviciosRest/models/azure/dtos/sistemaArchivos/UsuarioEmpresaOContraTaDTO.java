package backendhm.serviciosRest.models.azure.dtos.sistemaArchivos;

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
public class UsuarioEmpresaOContraTaDTO {

    private long id;

    private String ruc;

    private long idUser;

    private String razonSocial;

    private String tipo;

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
