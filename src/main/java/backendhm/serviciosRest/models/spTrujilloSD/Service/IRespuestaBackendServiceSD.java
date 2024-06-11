package backendhm.serviciosRest.models.spTrujilloSD.Service;

import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestDatosPacienteDTO;
import backendhm.serviciosRest.models.spTrujilloSD.dto.RespuestaBackendDTOTSD;

public interface IRespuestaBackendServiceSD {
    RequestDatosPacienteDTO busquedaDatosPacienteDNI(long dni);
    RespuestaBackendDTOTSD registrarDatosPaciente(RequestDatosPacienteDTO requestDatosPacienteDTO);
    RespuestaBackendDTOTSD busquedaDniPorNOrden(long nOrden);
}
