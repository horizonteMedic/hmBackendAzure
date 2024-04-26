package backendhm.serviciosRest.models.azure.dtos;

import jakarta.validation.constraints.Email;
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
public class EmpleadoDTO {

    private long id_empleado;

    private long numDocumento;

    private String tipoDoc;

    private String nombres;

    private String apellidos;

    private String cargo;

    private String ubigeo;

    private String cip;

    @Email
    private String correoElect;


    private String celular;

    private String telFijo;

    private String direccion;

    private Boolean estado;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;

    private String userRegistro;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaActualizacion;

    private String userActualizacion;

}
