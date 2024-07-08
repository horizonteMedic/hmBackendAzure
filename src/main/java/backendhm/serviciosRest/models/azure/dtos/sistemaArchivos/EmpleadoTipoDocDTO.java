package backendhm.serviciosRest.models.azure.dtos.sistemaArchivos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoTipoDocDTO {


    private Long id_empleado_tipo_doc;

    private long dni;

    private  String tipoArchivo;

    private String nombreArchivo;

    private String ruta;

    private String extension;

    private String base64;

}
