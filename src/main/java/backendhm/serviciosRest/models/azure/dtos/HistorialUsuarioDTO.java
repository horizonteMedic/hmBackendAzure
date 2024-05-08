package backendhm.serviciosRest.models.azure.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialUsuarioDTO {

    private Long id;

    private String tipoDocumento;

    private long numDocumento;

    private String apellidos;

    private String nombres;

    private String usuario;

    private String rol;

    private Boolean vigente;

    private long idUser;

    private long idUserRol;

    private long idEmpleado;
}
