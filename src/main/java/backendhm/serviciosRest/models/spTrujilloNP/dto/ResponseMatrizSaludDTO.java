package backendhm.serviciosRest.models.spTrujilloNP.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMatrizSaludDTO {
    long nOrden;
    String fechaSolicitud;
    String apellidosNombres;
    Integer dni;
    String fechaNacimiento;
    Integer edad;
    String razonContrata;
    String cargo;
    String tipoTrabajo;
    String carnetVacunacion;
    String aptitudEmo;
    String fechaEvalacuacion;
    String peso;
    String talla;
    String imc;
    String dxPeso;
    String glucosa;
    String colesterol;
    String trigliceridos;
    String dxOftalmo;
    String dxAudiometria;
    String restricciones;
    String clinica;
    String telefono;


}
