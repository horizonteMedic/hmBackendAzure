package backendhm.serviciosRest.models.azure.dtos;

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
public class ArchivoServidorDTO {

    private Long id;

    private Long dni;

    private Long historiaClinica;

    private String rutaArchivo;

    private String nombreArchivo;

    private String servidor;

    private Boolean estado;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;

    private String userRegistro;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaActualizacion;

    private String userActualizacion;

    private long id_tipo_archivo;

    private String fileBase64;

}
