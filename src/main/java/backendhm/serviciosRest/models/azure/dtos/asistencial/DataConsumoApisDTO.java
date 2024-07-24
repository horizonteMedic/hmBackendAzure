package backendhm.serviciosRest.models.azure.dtos.asistencial;

import lombok.Data;

@Data
public class DataConsumoApisDTO {
    private String dni;
    private String nombres;
    private String apellido_paterno;
    private String apellido_materno;
    private String fecha_nacimiento;
    private String sexo;
    private String direccion;
    private String ubigeo;
    private String distrito;
    private String provincia;
    private String departamento;
    private String estado_civil;
}
