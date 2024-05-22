package backendhm.serviciosRest.models.spTrujilloNP.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDatosPacienteDTO {
    private long codPa;

    private String nombresPa;

    private String fechaNaciminetoPa;

    private String sexoPa;

    private String emailPa;

    private String lugarNacPa;

    private String nivelEstPa;

    private String ocupacionPa;

    private String estadoCivilPa;

    private String direccionPa;

    private String departamentoPa;

    private String provinciaPa;

    private String distritoPa;

    private String caserioPA;

    private String fotoPa;

    private long codAleatorioPa;

    private String telCasaPa;

    private String telTrabajoPa;

    private String celPa;

    private String fechaRegistroPa;

    private String apellidosPa;

    private String horaRegistroPa;

    private long tipoDoc;

}
